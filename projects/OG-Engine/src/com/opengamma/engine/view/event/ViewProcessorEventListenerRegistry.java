/**
 * Copyright (C) 2009 - 2011 by OpenGamma Inc.
 * 
 * Please see distribution for license.
 */
package com.opengamma.engine.view.event;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Registered listeners for registering and unregistering ViewProcessorEventListener and sending notifications to registrants.
 *  <p/>
 * There is one of these per ViewProcessor. It is a composite listener.
 */
public class ViewProcessorEventListenerRegistry implements ViewProcessorEventListener {

  /**
   * The set of listeners.
   */
  private final CopyOnWriteArraySet<ViewProcessorEventListener> _listeners = new CopyOnWriteArraySet<ViewProcessorEventListener>();

  @Override
  public void notifyViewAdded(String viewName) {
    for (ViewProcessorEventListener listener : _listeners) {
      listener.notifyViewAdded(viewName);
    }
  }

  @Override
  public void notifyViewRemoved(String viewName) {
    for (ViewProcessorEventListener listener : _listeners) {
      listener.notifyViewRemoved(viewName);
    }
  }

  /**
   * Adds a listener to the notification service. No guarantee is made that listeners will be
   * notified in the order they were added.
   *
   * @param viewProcessorEventListener the listener to add. Can be null, in which case nothing happens
   * @return true if the listener is being added and was not already added
   */
  public final boolean registerListener(ViewProcessorEventListener viewProcessorEventListener) {
    if (viewProcessorEventListener == null) {
      return false;
    }
    return _listeners.add(viewProcessorEventListener);
  }

  /**
   * Removes a listener from the notification service.
   *
   * @param viewProcessorEventListener the listener to remove
   * @return true if the listener was present
   */
  public final boolean unregisterListener(ViewProcessorEventListener viewProcessorEventListener) {
    return _listeners.remove(viewProcessorEventListener);
  }

}
