package it.uniroma3.dia.tartbrain.util;

import it.uniroma3.dia.tartbrain.model.Role;

import java.math.BigInteger;
import java.util.Locale.Category;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ManagedBean(name = "roleConverterBean") 
@FacesConverter(value = "roleConverter")
public class RoleConverter implements Converter {

    @PersistenceContext(unitName = "tartbrain-web")
    private transient EntityManager entityManager;  

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component,
            String value) {
    
      return entityManager.find(Role.class, new Long(value)); 
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
       Role role;
       role = (Role)o;
        return String.valueOf(role.getId());
    }
}