import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.SerializationHelper;

public class IDS extends Server {
    private Classifier model;

    public IDS(String modelFilePath, String arffFilePath) throws Exception {
        super(arffFilePath);
        model = (Classifier) SerializationHelper.read(modelFilePath);
    }
    public String predict(Instance instance) throws Exception {
        double label = model.classifyInstance(instance);
        return instance.classAttribute().value((int) label);
    }

    public boolean isPredictionCorrect(Instance instance) throws Exception {
        String actualLabel = instance.stringValue(instance.classIndex());
        String predictedLabel = predict(instance);
        return actualLabel.equals(predictedLabel);
    }
    public String resultLabel(Instance instance) throws Exception {
        String actualLabel = instance.stringValue(instance.classIndex());
        String predictedLabel = predict(instance);
        return ("Actual: " + actualLabel + ","+ " Predicted: " + predictedLabel);
    }
}
