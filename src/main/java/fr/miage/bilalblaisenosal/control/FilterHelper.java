
package fr.miage.bilalblaisenosal.control;

import java.util.ArrayList;

/**
 *
 * @author Maxime BLAISE
 */
public class FilterHelper<K> {
    
    /**
     * Filtre choisi.
     */
    private String filter;
    
    /**
     * Liste contenant toutes les instances.
     */
    private ArrayList<K> listAll;

    public FilterHelper(String filter, ArrayList<K> listAll) {
        this.filter = filter;
        this.listAll = listAll;
    }
    
    public ArrayList<K> getWithFilter() {
        
        return null;
    }
    
    public ArrayList<K> getWithFilter(String filterParam) {
        
        return null;
    }

    /* GETTER AND SETTER */
    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public ArrayList<K> getListAll() {
        return listAll;
    }

    public void setListAll(ArrayList<K> listAll) {
        this.listAll = listAll;
    }
    
    
}
