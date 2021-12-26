package src;

import java.util.ArrayList;

public class Premio {
    private int idPremio;
    private double dinero;
    private int categoria;
    private ArrayList<Premio>listaDePremios= new ArrayList<>();
    comandosSql comando = new comandosSql();

    public Premio(double dinero, int categoria) {
        this.dinero = dinero;
        this.categoria = categoria;
    }
    public Premio(){
    }
    public Premio(int id,double dinero, int categoria) {
        this.idPremio=id;
        this.dinero = dinero;
        this.categoria = categoria;
    }
    
    public int getIdPremio() {
        return idPremio;
    }
    public void setIdPremio(int idPremio) {
        this.idPremio = idPremio;
    }
    public double getDinero() {
        return dinero;
    }
    public void setDinero(double dinero) {
        this.dinero = dinero;
    }
    public int getCategoria() {
        return categoria;
    }
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
    public void listarPremio(){
        listaDePremios= comando.listarPremios();
    }
    public void imprimir(){
        listarPremio();
        for(int i = 0;i<listaDePremios.size();i++){
            System.out.println("id: "+listaDePremios.get(i).getIdPremio()+" Dinero:"+listaDePremios.get(i).getDinero()+" Categoria:"+listaDePremios.get(i).getCategoria() );
        }
    }
    public double getPremioDinero(int categoria){
        listarPremio();
        double dinero = 0;
        for(int i=0;i<listaDePremios.size();i++){
            if(listaDePremios.get(i).getCategoria()==categoria){
                dinero = listaDePremios.get(i).getDinero();
            }
        }
        return dinero;
    }
 
    
}
