import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.File;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws Exception {
        ArffLoader loader = new ArffLoader();
        loader.setFile(new File("/Users/tanavchangal/split/test-20.arff"));
        Instances data = loader.getDataSet();
        data.setClassIndex(data.numAttributes() - 2); 

        split.splitAndSaveByType(data);
    }
}
