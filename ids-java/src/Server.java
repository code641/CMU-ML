import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Server {
    private int status;
    private Instances data;
    private String arffFile;
    public Server(String arffFilePath) throws Exception {
        arffFile = arffFilePath;
        DataSource source = new DataSource(arffFilePath);
        data = source.getDataSet();
        if (data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 2);
        }
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public Instance generateData() {

        Random random = new Random();
        List<Instance> candidates = new ArrayList<>();
        String[] allAttacks = {"normal", "neptune", "warezclient", "ipsweep", "portsweep", "teardrop", "nmap", "satan", "smurf", "pod", "back", "guess_passwd", "ftp_write", "multihop", "rootkit", "buffer_overflow", "imap", "warezmaster", "phf","land", "loadmodule", "spy", "perl", "saint", "mscan", "apache2", "snmpgetattack", "processtable", "httptunnel", "ps", "snmpguess","mailbomb", "named", "sendmail", "xterm", "worm", "xlock", "xsnoop","sqlattack", "udpstorm"};
        for (int i = 0; i < data.numInstances(); i++) {
            Instance instance = data.instance(i);
            if (instance.stringValue(instance.classIndex()).equals(allAttacks[status])){
                candidates.add(instance);
            }
        }
    
        if (!candidates.isEmpty()) {
            return candidates.get(random.nextInt(candidates.size()));
        } else {
            return null;
        }
    }
    public Instances returnDataOfType(int status) throws IOException{
        String[] allAttacks = {"normal", "neptune", "warezclient", "ipsweep", "portsweep", "teardrop", "nmap", "satan", "smurf", "pod", "back", "guess_passwd", "ftp_write", "multihop", "rootkit", "buffer_overflow", "imap", "warezmaster", "phf","land", "loadmodule", "spy", "perl", "saint", "mscan", "apache2", "snmpgetattack", "processtable", "httptunnel", "ps", "snmpguess","mailbomb", "named", "sendmail", "xterm", "worm", "xlock", "xsnoop","sqlattack", "udpstorm"};

        String arffFilePath = "/Users/tanavchangal/Desktop/CMU/ids-java/arff_files/" + allAttacks[status] + ".arff";

        ArffLoader loader = new ArffLoader();
        loader.setFile(new File(arffFilePath));
        weka.core.Instances data = loader.getDataSet();
        data.setClassIndex(data.numAttributes() - 2); // set class index if necessary
        return data;
    }
    

    }

