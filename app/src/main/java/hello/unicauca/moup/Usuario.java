package hello.unicauca.moup;

public class Usuario {


    private String nombre;
    private String descripcion;
    private String oferta;
    private String placa;
    private int foto;

    public Usuario(String nombre, String descripcion, String oferta, String placa, int foto){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.oferta = oferta;
        this.placa = placa;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getOferta() {return oferta;}
    public void setOferta(String oferta) {this.oferta = oferta; }

    public String getPlaca() {return placa;}
    public void setPlaca(String placa) {this.placa = placa; }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }


}

