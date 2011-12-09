/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.web.server.push;

import org.eclipse.jetty.continuation.Continuation;
import org.eclipse.jetty.continuation.ContinuationSupport;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This manages long-polling http requests.  It assumes the URL will be {@code <servlet path>/{clientId}}.
 */
public class LongPollingServlet extends SpringConfiguredServlet {

  /* package */ static final String RESULTS = "RESULTS";
  public static final String CLIENT_ID = "clientId";

  @Autowired
  private LongPollingConnectionManager _connectionManager;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Continuation continuation = ContinuationSupport.getContinuation(request);
    if (continuation.isExpired()) {
      // timeout - just send a blank reponse and tell the connection that its continuation has timed out
      String clientId = (String) continuation.getAttribute(CLIENT_ID);
      if (clientId != null) {
        // TODO will this always get the correct continuation?
        _connectionManager.timeout(clientId, continuation);
      }
      return;
    }
    String results = (String) request.getAttribute(RESULTS);
    // if this is the first time the request has been dispatched the results will be null. if the request has been
    // dispatched before and is being dispatched again after its continuation was resumed the results will be populated
    if (results == null) {
      setUpConnection(continuation, request, response);
    } else {
      // Send the results
      response.getWriter().write(results);
    }
  }

  private void setUpConnection(Continuation continuation, HttpServletRequest request, HttpServletResponse response) throws IOException {
    // suspend the request
    continuation.suspend(); // always suspend before registration
    String userId = request.getRemoteUser(); // TODO is this right?
    // get the client ID from the URL and pass the continuation to the connection manager for the next updates
    String clientId = getClientId(request);
    boolean connected = (clientId != null) && _connectionManager.connect(userId, clientId, continuation);
    if (!connected) {
      // couldn't get the client ID from the URL or the client ID didn't correspond to a known client
      response.sendError(404);
      continuation.complete();
    }
    continuation.setAttribute(CLIENT_ID, clientId);
  }

  /**
   * Extracts the client ID from the URL.  If the URL is {@code http://<host>/<servlet path>/12345} the client ID is 12345.
   * @param request The request
   * @return The client ID from {@code request}'s URL or {@code null} if it's missing
   */
  /* package */ static String getClientId(HttpServletRequest request) {
    // this is the portion of the URL after the part used to direct it to this servlet
    // i.e. if the full URL is http://host/subscription/abcd then suffix=/abcd
    String suffix = request.getRequestURI().substring(request.getServletPath().length());
    String clientId;
    if (suffix.length() > 1) {
      clientId = suffix.substring(1);
    } else {
      clientId = null;
    }
    return clientId;
  }
}
