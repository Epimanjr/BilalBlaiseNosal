
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
        ArrayList<K> res = new ArrayList<K>();
        
        // Loop
        for(K tmp: this.listAll) {
            if(tmp.toString().toLowerCase().contains(this.filter)) {
                res.add(tmp);
            }
        }
        
        return res;
    }
    
    public ArrayList<K> getWithFilter(String filterParam) {
        this.setFilter(filterParam);
        return this.getWithFilter();
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
