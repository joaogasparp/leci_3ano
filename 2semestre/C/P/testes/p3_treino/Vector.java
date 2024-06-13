import java.util.ArrayList;
import java.util.List;

public class Vector extends Value{
    private List<Double> values = new ArrayList<>();
    
    public Vector(List<Double> values) {
        this.values = values;
        setVector(true);
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }

    public List<Double> getValues() {
        return values;
    }

    @Override
    public String toString() {
        return values.toString();
    }

    @Override
    public Value symmetric() {
        List<Double> tmp = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            tmp.add(i, values.get(i) * -1);
        }
        return new Vector(tmp);
    }

    @Override
    public Value sum(Value v) {
        List<Double> tmp = new ArrayList<>();
        Vector v1 = (Vector) v;
        for (int i = 0; i < this.values.size(); i++) {
            tmp.add(i, this.values.get(i) + v1.getValues().get(i));
        }
        return new Vector(tmp);
    }

    @Override
    public Value subtract(Value v) {
        List<Double> tmp = new ArrayList<>();
        Vector v1 = (Vector) v;
        for (int i = 0; i < this.values.size(); i++) {
            tmp.add(i, this.values.get(i) - v1.getValues().get(i));
        }
        return new Vector(tmp);
    }
}
