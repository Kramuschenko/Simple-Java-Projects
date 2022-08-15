import java.io.*;
import java.util.ArrayList;

public class CargoShipWithCollections {

    //file input/output stream
    static ArrayList<container> containers = new ArrayList<>();
    static ArrayList<contAirtight> airtight = new ArrayList<>();
    static ArrayList<contGranular> granular = new ArrayList<>();
    static ArrayList<contСistern> cisterns = new ArrayList<>();

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

        System.out.println("Containers: " + containers.size());
        System.out.println("Granular: " + granular.size());
        System.out.println("Airtights: " + airtight.size());
        System.out.println("Cisterns: " + cisterns.size());

        System.out.println();


        outputStreamSorted1 outputStream = new outputStreamSorted1(containers, airtight ,granular , cisterns);

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
                        cisterns.add(new contСistern(ind, we, b));
                    }else if (category.equals("default")){
                        containers.add(new container(ind, we, b));
                    }else if (category.equals("airtight")){
                        airtight.add(new contAirtight(ind, we, b));
                    }else if (category.equals("granular")){
                        granular.add(new contGranular(ind, we, b));
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

class outputStreamSorted1{
    public outputStreamSorted1(ArrayList<CargoShipWithCollections.container> containers , ArrayList<CargoShipWithCollections.contAirtight> airtight , ArrayList<CargoShipWithCollections.contGranular> granular , ArrayList<CargoShipWithCollections.contСistern> cisterns) {

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




                            for(CargoShipWithCollections.container cont : containers){
                                if (cont.getWeight() >= maxWeight){
                                    maxWeight = cont.getWeight();
                                    isCont = true;
                                    index = containers.indexOf(cont);
                                }
                            }
                            for(CargoShipWithCollections.contAirtight cont : airtight){
                                if (cont.getWeight() >= maxWeight){
                                    maxWeight = cont.getWeight();
                                    isCont = false;
                                    isAirtight = true ;
                                    index = airtight.indexOf(cont);
                                }
                            }
                            for(CargoShipWithCollections.contGranular cont : granular){
                                if (cont.getWeight() >= maxWeight){
                                    maxWeight = cont.getWeight();
                                    isCont = false;
                                    isAirtight = false ;
                                    isGranular = true ;
                                    index = granular.indexOf(cont);
                                }
                            }
                            for(CargoShipWithCollections.contСistern cont : cisterns){
                                if (cont.getWeight() >= maxWeight){
                                    maxWeight = cont.getWeight();
                                    isCont = false;
                                    isAirtight = false ;
                                    isGranular = false ;
                                    isCistern = true;
                                    index = cisterns.indexOf(cont);
                                }
                            }

                            int x = j + 1;
                            int y = i + 1;
                            int z = l + 1;
                            int line = k + 1;

                            if (isCont && !containers.isEmpty()){
                                text = " (X: " + x + "; Y: " + y + "; Z: " + z + "; Line: " +  line + "; Index: " + containers.get(index).getIndex() +  "; Category: " + containers.get(index).getCategory() +  "; Weight: " + containers.get(index).getWeight() +  "; Electric: " + containers.get(index).isElectro() + ") " + '\t';
                                mybytes = text.getBytes();

                                containers.remove(containers.get(index));
                            }else if (isAirtight && !airtight.isEmpty()){

                                text = " (X: " + x + "; Y: " + y + "; Z: " + z + "; Line: " +  line + "; Index: " + airtight.get(index).getIndex() +  "; Category: " + airtight.get(index).getCategory() +  "; Weight: " + airtight.get(index).getWeight() +  "; Electric: " + airtight.get(index).isElectro() + ") " + '\t';
                                mybytes = text.getBytes();

                                airtight.remove(airtight.get(index));

                            }else if (isGranular && !granular.isEmpty()){

                                text = " (X: " + x + "; Y: " + y + "; Z: " + z + "; Line: " +  line + "; Index: " + granular.get(index).getIndex() +  "; Category: " + granular.get(index).getCategory() +  "; Weight: " + granular.get(index).getWeight() +  "; Electric: " + granular.get(index).isElectro() + ") " + '\t';
                                mybytes = text.getBytes();

                                granular.remove(granular.get(index));

                            }else if (isCistern && !cisterns.isEmpty()){

                                text = " (X: " + x + "; Y: " + y + "; Z: " + z + "; Line: " +  line + "; Index: " + cisterns.get(index).getIndex() + "; Category: " + cisterns.get(index).getCategory() +  "; Weight: " + cisterns.get(index).getWeight() +  "; Electric: " + cisterns.get(index).isElectro() + ") " + '\t';
                                mybytes = text.getBytes();

                                cisterns.remove(cisterns.get(index));
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