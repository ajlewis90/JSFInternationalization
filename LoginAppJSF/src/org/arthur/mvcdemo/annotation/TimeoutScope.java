package org.arthur.mvcdemo.annotation;

import java.util.Collections;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PreDestroyCustomScopeEvent;
import javax.faces.event.ScopeContext;

@ManagedBean(name="timeoutScope")
@SessionScoped

public class TimeoutScope extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    @Override
    public Object put(String name, Object bean) {
        MySessionScoped timeout = bean.getClass().getAnnotation(MySessionScoped.class);

        if (timeout == null) {
            throw new IllegalArgumentException("@Timeout annotation is required on bean " + name);
        }

        Long endtime = System.nanoTime() + (timeout.value() * (long) 6e10);
        Object[] beanAndEndtime = new Object[] { bean, endtime };
        return super.put(name, beanAndEndtime);
    }

    @Override
    public Object get(Object key) {
        Object[] beanAndEndtime = (Object[]) super.get(key);

        if (beanAndEndtime == null) {
            return null;
        }

        Object bean = beanAndEndtime[0];
        Long endtime = (Long) beanAndEndtime[1];

        if (System.nanoTime() > endtime) {
            String name = (String) key;
            ScopeContext scope = new ScopeContext("timeoutScope", Collections.singletonMap(name, bean));
            FacesContext context = FacesContext.getCurrentInstance();
            context.getApplication().publishEvent(context, PreDestroyCustomScopeEvent.class, scope);
            return null;
        }

        return bean;
    }

}
