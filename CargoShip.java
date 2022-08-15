
import java.io.*;

public class CargoShip {

    //file input/output stream
    static container[] containers = new container[0];
    static contAirtight[] airtight = new contAirtight[0];
    static contGranular[] granular = new contGranular[0];
    static contСistern[] cisterns = new contСistern[0];

    public static void main(String[] args) {


        File file1 = new File("./src/containers.txt");
        try {
            if (file1.createNewFile()){
                System.out.println("File is created!");
            }
            else{
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream fos = new FileOutputStream(file1)){
            for (int i = 0; i < 15000; i++) {
                int rand = (int)(Math.random()*4);

                switch (rand){
                    case 0 -> {
                        double randBool = Math.random();
                        boolean bool;

                        bool= randBool >= 0.5;

                        contAirtight ca = new contAirtight(i , (int)(Math.random()*500+1500) , bool);
                        String text = ca.getCategory() + " " + ca.getIndex() + " " + ca.getWeight() + " " + ca.isElectro() + " "  +'\t';
                        byte[] mybytes = text.getBytes();
                        fos.write(mybytes);

                    }
                    case 1 -> {
                        contGranular cg = new contGranular(i , (int)(Math.random()*300+1800) , false);
                        String text = cg.getCategory() + " " + cg.getIndex() + " " + cg.getWeight() + " " + cg.isElectro() + " "  +'\t';
                        byte[] mybytes = text.getBytes();
                        fos.write(mybytes);
                    }
                    case 2 -> {

                        double randBool = Math.random();
                        boolean bool;

                        bool= randBool >= 0.5;

                        contСistern cg = new contСistern(i ,(int)(Math.random()*300+2200) , bool);
                        String text = cg.getCategory() + " " + cg.getIndex() + " " + cg.getWeight() + " " + cg.isElectro() + " "  +'\t';
                        byte[] mybytes = text.getBytes();
                        fos.write(mybytes);
                    }
                    case 3 -> {
                        double randBool = Math.random();
                        boolean bool;

                        bool= randBool >= 0.5;

                        container cont = new container(i , (int)(Math.random()*2000+500) , bool);
                        String text = cont.getCategory() + " " + cont.getIndex() + " " + cont.getWeight() + " " + cont.isElectro() + " " +'\t';
                        byte[] mybytes = text.getBytes();
                        fos.write(mybytes);
                    }
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        inputStream();

        System.out.println("Simple containers: " + containers.length);
        System.out.println("Airtight containers: " + airtight.length);
        System.out.println("Granular containers: " + granular.length);
        System.out.println("Cistern containers: " + cisterns.length);
        System.out.println("All: " + (cisterns.length + granular.length + airtight.length + containers.length));

        outputStreamSorted outputStream = new outputStreamSorted(containers, airtight ,granular , cisterns);

    }

    static class container{
        private int index;
        private int weight;
        private boolean electro;
        private String category;

        public String getCategory() {
            return category;
        }

        public int getIndex() {
            return index;
        }

        public int getWeight() {
            return weight;
        }

        public boolean isElectro() {
            return electro;
        }

        public container(int index, int weight, boolean electro) {
            this.index = index;
            this.weight = weight;
            this.electro = electro;
            category = "default";
        }
    }

    static class contAirtight extends container{

        private boolean airtight;
        private String category;

        public boolean isAirtight() {
            return airtight;
        }

        public String getCategory() {
            return category;
        }

        public contAirtight(int index, int weight, boolean electro) {
            super( index ,weight, electro );
            category = "airtight";
            airtight = true;
        }
    }

    static class contGranular extends container{

        private boolean granular;
        private String category;

        public contGranular(int index, int weight, boolean electro) {
            super(index ,weight, electro);
            category="granular";
            granular = true;
        }

        public boolean isGranular() {
            return granular;
        }

        public String getCategory() {
            return category;
        }
    }

    static class contСistern  extends container{

        private boolean liquid;
        private String category;

        public contСistern(int index, int weight, boolean electro) {
            super(index ,weight, electro);
            category="cistern";
            liquid = true;
        }

        public boolean isLiquid() {
            return liquid;
        }

        public String getCategory() {
            return category;
        }
    }


    static void inputStream(){
        try (FileInputStream fis = new FileInputStream("./src/containers.txt");){

            int i , counter = 0;

            String category = "";
            String index = "";
            String weight = "";
            String isElectro = "";

            while ((i = fis.read()) != -1){
                if((char)i == ' '){
                    counter++;
                }
                else if((char)i == '\t'){
                    int ind = Integer.parseInt(index);
                    int we = Integer.parseInt(weight);
                    boolean b;

                    if (isElectro.equals("true")){
                        b = true;
                    }else{
                        b = false;
                    }


                    if (category.equals("cistern")){

                        contСistern[] cisternsTMP = new contСistern[cisterns.length+1];

                        cisternsTMP[0] = new contСistern(ind, we, b);


                        for (int j = 0; j < cisterns.length; j++) {
                            cisternsTMP[j+1] = cisterns[j];
                        }

                        cisterns = cisternsTMP;

                    }else if (category.equals("default")){


                        container[] containerTMP = new container[containers.length+1];

                        containerTMP[0] = new container(ind, we, b);


                        for (int j = 0; j < containers.length; j++) {
                            containerTMP[j+1] = containers[j];
                        }

                        containers = containerTMP;

                    }else if (category.equals("airtight")){


                        contAirtight[] airtightTMP = new contAirtight[airtight.length+1];
                        airtightTMP[0] = new contAirtight(ind, we, b);

                        for (int j = 0; j < airtight.length; j++) {
                            airtightTMP[j+1] = airtight[j];
                        }

                        airtight = airtightTMP;

                    }else if (category.equals("granular")){

                        contGranular[] granularTMP = new contGranular[granular.length+1];
                        granularTMP[0] = new contGranular(ind, we, b);

                        for (int j = 0; j < granular.length; j++) {
                            granularTMP[j+1] = granular[j];
                        }
                        granular = granularTMP;

                    }

                    category = "";
                    index = "";
                    weight = "";
                    isElectro = "";
                    counter = 0;

                }else{
                    switch (counter){
                        case 0 -> {
                            category += (char)i;
                        }
                        case 1 -> {
                            index += (char)i;
                        }
                        case 2 -> {
                            weight += (char)i;
                        }
                        case 3 -> {
                            isElectro += (char)i;
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class outputStreamSorted{
    public outputStreamSorted(CargoShip.container[] containers , CargoShip.contAirtight[] airtight , CargoShip.contGranular[] granular , CargoShip.contСistern[] cisterns) {

        File file1 = new File("./src/ship_sorted.txt");
        try {
            if (file1.createNewFile()){
                System.out.println("File is created!");
            }
            else{
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (FileOutputStream fos = new FileOutputStream(file1)){


            for (int i = 0; i < 250; i++) {
                char newLine = '\n';

                for (int j = 0; j < 5; j++) {

                    for (int k = 0; k < 2; k++) {

                        String tmp1 = "", tmp2 = "" , tmp3 = "" , tmp4 = "" , tmp5 = "" , tmp6 = "";

                        for (int l = 0; l < 6; l++) {



                            String text = "";
                            byte[] mybytes = text.getBytes();

                            int maxWeight=0;
                            int index=0;
                            boolean isCont = false , isAirtight = false , isGranular = false , isCistern = false;


                            for (int m = 0; m < containers.length; m++) {
                                if (containers[m].getWeight() >= maxWeight){
                                    maxWeight = containers[m].getWeight();
                                    isCont = true;
                                    index = m;
                                }
                            }


                            for(int m = 0; m < airtight.length; m++){
                                if (airtight[m].getWeight() >= maxWeight){
                                    maxWeight = airtight[m].getWeight();
                                    isCont = false;
                                    isAirtight = true ;
                                    index = m;
                                }
                            }
                            for(int m = 0; m < granular.length; m++){
                                if (granular[m].getWeight() >= maxWeight){
                                    maxWeight = granular[m].getWeight();
                                    isCont = false;
                                    isAirtight = false ;
                                    isGranular = true ;
                                    index = m;
                                }
                            }
                            for(int m = 0; m < cisterns.length; m++){
                                if (cisterns[m].getWeight() >= maxWeight){
                                    maxWeight = cisterns[m].getWeight();
                                    isCont = false;
                                    isAirtight = false ;
                                    isGranular = false ;
                                    isCistern = true;
                                    index=m;
                                }
                            }

                            int x = j + 1;
                            int y = i + 1;
                            int z = l + 1;
                            int line = k + 1;

                            if (isCont && containers.length != 0){
                                text = " (X: " + x + "; Y: " + y + "; Z: " + z + "; Line: " +  line + "; Index: " + containers[index].getIndex() +  "; Category: " + containers[index].getCategory() +  "; Weight: " + containers[index].getWeight() +  "; Electric: " + containers[index].isElectro() + ") " + '\t';
                                mybytes = text.getBytes();

                                CargoShip.container[] containersTMP = new CargoShip.container[containers.length-1];

                                int roznica = 0;

                                for (int m = 0; m < containers.length; m++) {
                                    if (containers[index] != containers[m])
                                        containersTMP[m-roznica] = containers[m];
                                    else
                                        roznica = 1;
                                }

                                containers = containersTMP;


                            }else if (isAirtight && airtight.length != 0){

                                text = " (X: " + x + "; Y: " + y + "; Z: " + z + "; Line: " +  line + "; Index: " + airtight[index].getIndex() +  "; Category: " + airtight[index].getCategory() +  "; Weight: " + airtight[index].getWeight() +  "; Electric: " + airtight[index].isElectro() + ") " + '\t';
                                mybytes = text.getBytes();


                                CargoShip.contAirtight[] airtightTMP = new CargoShip.contAirtight[airtight.length-1];

                                int roznica = 0;

                                for (int m = 0; m < airtight.length; m++) {
                                    if (airtight[index] != airtight[m])
                                        airtightTMP[m-roznica] = airtight[m];
                                    else
                                        roznica = 1;
                                }

                                airtight = airtightTMP;


                            }else if (isGranular && granular.length != 0){

                                text = " (X: " + x + "; Y: " + y + "; Z: " + z + "; Line: " +  line + "; Index: " + granular[index].getIndex() +  "; Category: " + granular[index].getCategory() +  "; Weight: " + granular[index].getWeight() +  "; Electric: " + granular[index].isElectro() + ") " + '\t';
                                mybytes = text.getBytes();


                                int roznica = 0;
                                CargoShip.contGranular[] granularTMP = new CargoShip.contGranular[granular.length-1];

                                for (int m = 0; m < granular.length; m++) {
                                    if (granular[index] != granular[m])
                                        granularTMP[m-roznica] = granular[m];
                                    else
                                        roznica = 1;
                                }

                                granular = granularTMP;




                            }else if (isCistern && cisterns.length != 0){

                                text = " (X: " + x + "; Y: " + y + "; Z: " + z + "; Line: " +  line + "; Index: " + cisterns[index].getIndex() + "; Category: " + cisterns[index].getCategory() +  "; Weight: " + cisterns[index].getWeight() +  "; Electric: " + cisterns[index].isElectro() + ") " + '\t';
                                mybytes = text.getBytes();

                                int roznica = 0;
                                CargoShip.contСistern[] cisternsTMP = new CargoShip.contСistern[cisterns.length-1];

                                for (int m = 0; m < cisterns.length; m++) {
                                    if (cisterns[index] != cisterns[m])
                                        cisternsTMP[m-roznica] = cisterns[m];
                                    else
                                        roznica = 1;
                                }

                                cisterns = cisternsTMP;

                            }

                            if (k%2 ==0){
                                fos.write(mybytes);
                            }else{
                                switch (l){
                                    case 0 -> tmp1 = text;
                                    case 1 -> tmp2 = text;
                                    case 2 -> tmp3 = text;
                                    case 3 -> tmp4 = text;
                                    case 4 -> tmp5 = text;
                                    case 5 -> tmp6 = text;

                                }
                            }
                        }

                        if (k%2 !=0){
                            byte[] mybytes1 = tmp6.getBytes();
                            fos.write(mybytes1);
                            mybytes1 = tmp5.getBytes();
                            fos.write(mybytes1);
                            mybytes1 = tmp4.getBytes();
                            fos.write(mybytes1);
                            mybytes1 = tmp3.getBytes();
                            fos.write(mybytes1);
                            mybytes1 = tmp2.getBytes();
                            fos.write(mybytes1);
                            mybytes1 = tmp1.getBytes();
                            fos.write(mybytes1);
                        }

                        fos.write(newLine);
                    }

                    fos.write(newLine);
                }

                int tmpY = i +1;
                String text1 = " Nowy rzad: (" + tmpY + ") \n";
                byte[] newRzad = text1.getBytes();

                fos.write(newRzad);

                fos.write(newLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
