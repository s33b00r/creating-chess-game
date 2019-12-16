package AI;

import java.io.Serializable;
import java.util.ArrayList;

class WeightData implements Serializable {

    public static final String FILENAME = "data";

    private ArrayList<Double> weights;


    public ArrayList<Double> getWeights() {
        return weights;
    }

    public void setWeights(ArrayList<Double> weights) {
        this.weights = weights;
    }
}
