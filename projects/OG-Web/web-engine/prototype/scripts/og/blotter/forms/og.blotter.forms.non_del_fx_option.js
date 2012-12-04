/**
 * Copyright 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * Please see distribution for license.
 */
$.register_module({
    name: 'og.blotter.forms.Non_del_fx_option',
    dependencies: [],
    obj: function () {   
        return function () {
            var constructor = this;
            constructor.load = function () {
                constructor.title = 'Non-Deliverable FX Option';
                var form = new og.common.util.ui.Form({
                    module: 'og.blotter.forms.fx_derivative_tash',
                    data: {},
                    type_map: {},
                    selector: '.OG-blotter-form-block',
                    extras:{}
                });
                form.children.push(
                    new form.Block({
                        module: 'og.blotter.forms.blocks.fx_derivative_value_tash',
                        extras: {}
                    }),                    
                    new form.Block({
                        module: 'og.blotter.forms.blocks.fx_derivative_date_tash',
                        extras: {}
                    })
                );
                form.dom();   
            }; 
            constructor.load();
            constructor.kill = function () {
            };
        };
    }
});