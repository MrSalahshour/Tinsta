package ir.sharif.math.ap99_2.tinsta_shared.model;



public class Model {
    protected int id;

    public Model() {
        this.id =(int) (System.currentTimeMillis() & 0xfffffff);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
