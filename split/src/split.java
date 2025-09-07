import weka.core.Instance;
import weka.core.Instances;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class split {

    public static void splitAndSaveByType(Instances data) throws IOException {
        Map<String, Instances> instancesByType = new HashMap<>();

        for (int i = 0; i < data.numClasses(); i++) {
            String className = data.classAttribute().value(i);
            Instances instancesOfType = new Instances(data, 0);
            instancesByType.put(className, instancesOfType);
        }

        for (int i = 0; i < data.numInstances(); i++) {
            Instance instance = data.instance(i);
            String className = instance.stringValue(instance.classIndex());
            instancesByType.get(className).add(instance);
        }

        for (Map.Entry<String, Instances> entry : instancesByType.entrySet()) {
            String className = entry.getKey();
            Instances instancesOfType = entry.getValue();
            saveInstancesToARFF(instancesOfType, className + ".arff");
        }
    }

    private static void saveInstancesToARFF(Instances instances, String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write(instances.toString());
        writer.flush();
        writer.close();
    }
}