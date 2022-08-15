import java.util.Scanner;

public class Chinczyk {

    static int iloscGraczy = 4;
    static int indexOfCurrentPlayer=0;
    static String[][] pole;
    static int losowanie = (int)(Math.random()*6+1);
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        iloscGraczy = 4;
        start();
    }

    public static void start() {
        pole();

        while(losowanie != 6){
            losowanie = (int)(Math.random()*6+1);
            if (indexOfCurrentPlayer < (iloscGraczy-1)){
                indexOfCurrentPlayer++;
            }else
                indexOfCurrentPlayer=0;
        }

        poczatekGry();


        while (true){

            losowanie = (int)(Math.random()*6+1);

            System.out.println("Wylosowano: " + losowanie + " , gracz: " + indexOfCurrentPlayer);


            if (CzyNieZaczalGre(indexOfCurrentPlayer) && losowanie == 6){

                System.out.println("Gracz: " + indexOfCurrentPlayer);
                System.out.print("podaj numer pola na ktorym stoi pionek ktory nalezy przesunac lub (44) jezeli chcesz dodac pionek do pola : ");
                int result = scanner.nextInt();
                System.out.println();

                if (result == 44){
                    poczatekGry();
                } else{
                    System.out.println("powtorz wybor: ");
                    ruch();
                }

            }else if (CzyIstnejaPionyNaPlaszczyznie(indexOfCurrentPlayer)){
                System.out.println("Gracz: " + indexOfCurrentPlayer);
                System.out.print("podaj numer pola na ktorym stoi pionek ktory nalezy przesunac: ");
                System.out.println();
                ruch();

            }

            if (indexOfCurrentPlayer < (iloscGraczy-1)){
                indexOfCurrentPlayer++;
            }else
                indexOfCurrentPlayer=0;

        }

    }

    public static void ruch(){

        String[][] poleTmp = pole;
        int result = scanner.nextInt();
        int yx = numeryPola(result);
        int x = yx % 100;
        yx /= 100;
        int y = yx;
        String tmp = pole[y][x];

        while (true){

            yx = numeryPola(result);
            x = yx % 100;
            yx /= 100;
            y = yx;
            tmp = pole[y][x];


            if (indexOfCurrentPlayer == 0 && tmp == "a  "){
                break;
            }else if(indexOfCurrentPlayer == 1 && tmp == "b  "){
                break;
            }else if(indexOfCurrentPlayer == 2 && tmp == "c  "){
                break;
            }else if(indexOfCurrentPlayer == 3 && tmp == "d  "){
                break;
            }else{
                System.out.println("Nie mozesz tak robic!");
                System.out.println("podaj numer pola na ktorym stoi pionek ktory nalezy przesunac:" );
                result = scanner.nextInt();
            }

        }

        int NewPosition = result;

        for (int i = 1; i <= losowanie; i++) {
            switch (indexOfCurrentPlayer){
                case 0 -> {
                    if (NewPosition == 38){
                        poleTmp[y][x] = "x  ";
                        if (poleTmp[5][6] == "   "){
                            poleTmp[5][6] = "a  ";
                            if (pole[1][10] == "   "){
                                pole[1][10] = "*  ";
                            }else if (pole[1][11] == "   "){
                                pole[1][11] = "*  ";
                            }else if (pole[2][10] == "   "){
                                pole[2][10] = "*  ";
                            }else if (pole[2][11] == "   "){
                                pole[2][11] = "*  ";
                            }
                            poleTmp[1][7] = "x  ";
                            poleTmp[1][6] = "x  ";
                        }else if (poleTmp[4][6] == "   "){
                            poleTmp[4][6] = "a  ";
                            if (pole[1][10] == "   "){
                                pole[1][10] = "*  ";
                            }else if (pole[1][11] == "   "){
                                pole[1][11] = "*  ";
                            }else if (pole[2][10] == "   "){
                                pole[2][10] = "*  ";
                            }else if (pole[2][11] == "   "){
                                pole[2][11] = "*  ";
                            }
                            poleTmp[1][7] = "x  ";
                            poleTmp[1][6] = "x  ";
                        }
                        else if (poleTmp[3][6] == "   "){
                            poleTmp[3][6] = "a  ";
                            if (pole[1][10] == "   "){
                                pole[1][10] = "*  ";
                            }else if (pole[1][11] == "   "){
                                pole[1][11] = "*  ";
                            }else if (pole[2][10] == "   "){
                                pole[2][10] = "*  ";
                            }else if (pole[2][11] == "   "){
                                pole[2][11] = "*  ";
                            }
                            poleTmp[1][7] = "x  ";
                            poleTmp[1][6] = "x  ";
                        }else if (poleTmp[2][6] == "   "){
                            poleTmp[2][6] = "a  ";
                            if (pole[1][10] == "   "){
                                pole[1][10] = "*  ";
                            }else if (pole[1][11] == "   "){
                                pole[1][11] = "*  ";
                            }else if (pole[2][10] == "   "){
                                pole[2][10] = "*  ";
                            }else if (pole[2][11] == "   "){
                                pole[2][11] = "*  ";
                            }
                            poleTmp[1][7] = "x  ";
                            poleTmp[1][6] = "x  ";
                        }else{
                            System.out.println("Wygral gracz \'a\'");
                            System.exit(0);
                        }
                        break;
                    }
                }
                case 1 -> {
                    if (NewPosition == 9 ){
                        poleTmp[y][x] = "x  ";
                        if (poleTmp[6][7] == "   "){
                            poleTmp[6][7] = "b  ";
                            if (pole[10][10] == "   "){
                                pole[10][10] = "*  ";
                            }else if (pole[10][11] == "   "){
                                pole[10][11] = "*  ";
                            }else if (pole[11][10] == "   "){
                                pole[11][10] = "*  ";
                            }else if (pole[11][11] == "   "){
                                pole[11][11] = "*  ";
                            }
                            poleTmp[7][11] = "x  ";
                            poleTmp[6][11] = "x  ";
                        }else if (poleTmp[6][8] == "   "){
                            poleTmp[6][8] = "b  ";
                            if (pole[10][10] == "   "){
                                pole[10][10] = "*  ";
                            }else if (pole[10][11] == "   "){
                                pole[10][11] = "*  ";
                            }else if (pole[11][10] == "   "){
                                pole[11][10] = "*  ";
                            }else if (pole[11][11] == "   "){
                                pole[11][11] = "*  ";
                            }
                            poleTmp[7][11] = "x  ";
                            poleTmp[6][11] = "x  ";

                        }
                        else if (poleTmp[6][9] == "   "){
                            poleTmp[6][9] = "b  ";
                            if (pole[10][10] == "   "){
                                pole[10][10] = "*  ";
                            }else if (pole[10][11] == "   "){
                                pole[10][11] = "*  ";
                            }else if (pole[11][10] == "   "){
                                pole[11][10] = "*  ";
                            }else if (pole[11][11] == "   "){
                                pole[11][11] = "*  ";
                            }
                            poleTmp[7][11] = "x  ";
                            poleTmp[6][11] = "x  ";

                        }else if (poleTmp[6][10] == "   "){
                            poleTmp[6][10] = "b  ";
                            if (pole[10][10] == "   "){
                                pole[10][10] = "*  ";
                            }else if (pole[10][11] == "   "){
                                pole[10][11] = "*  ";
                            }else if (pole[11][10] == "   "){
                                pole[11][10] = "*  ";
                            }else if (pole[11][11] == "   "){
                                pole[11][11] = "*  ";
                            }
                            poleTmp[7][11] = "x  ";
                            poleTmp[6][11] = "x  ";

                        }else{
                            System.out.println("Wygral gracz \'b\'");
                            System.exit(0);
                        }
                    }
                }
                case 2 -> {
                    if (NewPosition == 19 ){
                        poleTmp[y][x] = "x  ";
                        if (poleTmp[7][6] == "   "){
                            poleTmp[7][6] = "c  ";
                            if (pole[10][1] == "   "){
                                pole[10][1] = "*  ";
                            }else if (pole[10][2] == "   "){
                                pole[10][2] = "*  ";
                            }else if (pole[11][1] == "   "){
                                pole[11][1] = "*  ";
                            }else if (pole[11][2] == "   "){
                                pole[11][2] = "*  ";
                            }
                            poleTmp[11][5] = "x  ";
                            poleTmp[11][6] = "x  ";
                        }else if (poleTmp[8][6] == "   "){
                            poleTmp[8][6] = "c  ";
                            if (pole[10][1] == "   "){
                                pole[10][1] = "*  ";
                            }else if (pole[10][2] == "   "){
                                pole[10][2] = "*  ";
                            }else if (pole[11][1] == "   "){
                                pole[11][1] = "*  ";
                            }else if (pole[11][2] == "   "){
                                pole[11][2] = "*  ";
                            }
                            poleTmp[11][5] = "x  ";
                            poleTmp[11][6] = "x  ";
                        }
                        else if (poleTmp[9][6] == "   "){
                            poleTmp[9][6] = "c  ";
                            if (pole[10][1] == "   "){
                                pole[10][1] = "*  ";
                            }else if (pole[10][2] == "   "){
                                pole[10][2] = "*  ";
                            }else if (pole[11][1] == "   "){
                                pole[11][1] = "*  ";
                            }else if (pole[11][2] == "   "){
                                pole[11][2] = "*  ";
                            }
                            poleTmp[11][5] = "x  ";
                            poleTmp[11][6] = "x  ";
                        }else if (poleTmp[10][6] == "   "){
                            poleTmp[10][6] = "c  ";
                            if (pole[10][1] == "   "){
                                pole[10][1] = "*  ";
                            }else if (pole[10][2] == "   "){
                                pole[10][2] = "*  ";
                            }else if (pole[11][1] == "   "){
                                pole[11][1] = "*  ";
                            }else if (pole[11][2] == "   "){
                                pole[11][2] = "*  ";
                            }
                            poleTmp[11][5] = "x  ";
                            poleTmp[11][6] = "x  ";
                        }else{
                            System.out.println("Wygral gracz \'c\'");
                            System.exit(0);
                        }
                    }
                }
                case 3 -> {
                    if (NewPosition == 29 ){
                        poleTmp[y][x] = "x  ";
                        if (poleTmp[6][5] == "   "){
                            poleTmp[6][5] = "d  ";
                            if (pole[1][1] == "   "){
                                pole[1][1] = "*  ";
                            }else if (pole[1][2] == "   "){
                                pole[1][2] = "*  ";
                            }else if (pole[2][1] == "   "){
                                pole[2][1] = "*  ";
                            }else if (pole[2][2] == "   "){
                                pole[2][2] = "*  ";
                            }
                            poleTmp[5][1] = "x  ";
                            poleTmp[6][1] = "x  ";
                        }else if (poleTmp[6][4] == "   "){
                            poleTmp[6][4] = "d  ";
                            if (pole[1][1] == "   "){
                                pole[1][1] = "*  ";
                            }else if (pole[1][2] == "   "){
                                pole[1][2] = "*  ";
                            }else if (pole[2][1] == "   "){
                                pole[2][1] = "*  ";
                            }else if (pole[2][2] == "   "){
                                pole[2][2] = "*  ";
                            }poleTmp[5][1] = "x  ";
                            poleTmp[6][1] = "x  ";
                        }
                        else if (poleTmp[6][3] == "   "){
                            poleTmp[6][3] = "d  ";
                            if (pole[1][1] == "   "){
                                pole[1][1] = "*  ";
                            }else if (pole[1][2] == "   "){
                                pole[1][2] = "*  ";
                            }else if (pole[2][1] == "   "){
                                pole[2][1] = "*  ";
                            }else if (pole[2][2] == "   "){
                                pole[2][2] = "*  ";
                            }poleTmp[5][1] = "x  ";
                            poleTmp[6][1] = "x  ";
                        }else if (poleTmp[6][2] == "   "){
                            poleTmp[6][2] = "d  ";
                            if (pole[1][1] == "   "){
                                pole[1][1] = "*  ";
                            }else if (pole[1][2] == "   "){
                                pole[1][2] = "*  ";
                            }else if (pole[2][1] == "   "){
                                pole[2][1] = "*  ";
                            }else if (pole[2][2] == "   "){
                                pole[2][2] = "*  ";
                            }poleTmp[5][1] = "x  ";
                            poleTmp[6][1] = "x  ";
                        }else{
                            System.out.println("Wygral gracz \'d\'");
                            System.exit(0);
                        }
                    }
                }
            }
            NewPosition++;
        }

        if (NewPosition > 39)
            NewPosition %= 10;

        int yxNew = numeryPola(NewPosition);
        int xNew = yxNew % 100;
        yxNew /= 100;
        int yNew = yxNew;

        CzyZbic(yNew, xNew);

        poleTmp[y][x] = "x  ";

        poleTmp[yNew][xNew] = tmp;
        pole = poleTmp;
        show(pole);

    }

    public static boolean CzyNieZaczalGre(int current){

        switch (current){
            case 0 -> {
                return (((pole[1][10] == "a  ") || (pole[1][11] == "a  ") || (pole[2][10] == "a  ") || (pole[2][11] == "a  ")) || ((pole[1][10] == "*  ") || (pole[1][11] == "*  ") || (pole[2][10] == "*  ") || (pole[2][11] == "*  ")));
            }case 1 -> {
                return (((pole[10][10] == "b  ") || (pole[10][11] == "b  ") || (pole[11][10] == "b  ") || (pole[11][11] == "b  ")) || ((pole[10][10] == "*  ") || (pole[10][11] == "*  ") || (pole[11][10] == "*  ") || (pole[11][11] == "*  ")));
            }
            case 2 -> {
                return (((pole[10][1] == "c  ") || (pole[10][2] == "c  ") || (pole[11][1] == "c  ") || (pole[11][2] == "c  ")) || ((pole[10][1] == "*  ") || (pole[10][2] == "*  ") || (pole[11][1] == "*  ") || (pole[11][2] == "*  ")));
            }
            case 3 -> {
                return (((pole[1][1] == "d  ") || (pole[1][2] == "d  ") || (pole[2][1] == "d  ") || (pole[2][2] == "d  ")) || ((pole[1][1] == "*  ") || (pole[1][2] == "*  ") || (pole[2][1] == "*  ") || (pole[2][2] == "*  ")));
            }
            default -> {return false;}
        }

    }

    public static boolean CzyIstnejaPionyNaPlaszczyznie(int current){

        switch (current){
            case 0 -> {
                return !(((pole[1][10] == "a  ") && (pole[1][11] == "a  ") && (pole[2][10] == "a  ") && (pole[2][11] == "a  ")) || ((pole[1][10] == "*  ") && (pole[1][11] == "*  ") && (pole[2][10] == "*  ") && (pole[2][11] == "*  ")));
            }case 1 -> {
                return !(((pole[10][10] == "b  ") && (pole[10][11] == "b  ") && (pole[11][10] == "b  ") && (pole[11][11] == "b  ")) || ((pole[10][10] == "*  ") && (pole[10][11] == "*  ") && (pole[11][10] == "*  ") && (pole[11][11] == "*  ")));
            }
            case 2 -> {
                return !(((pole[10][1] == "c  ") && (pole[10][2] == "c  ") && (pole[11][1] == "c  ") && (pole[11][2] == "c  ")) || ((pole[10][1] == "*  ") && (pole[10][2] == "*  ") && (pole[11][1] == "*  ") && (pole[11][2] == "*  ")));
            }
            case 3 -> {
                return !(((pole[1][1] == "d  ") && (pole[1][2] == "d  ") && (pole[2][1] == "d  ") && (pole[2][2] == "d  ")) || ((pole[1][1] == "*  ") && (pole[1][2] == "*  ") && (pole[2][1] == "*  ") && (pole[2][2] == "*  ")));
            }
            default -> {
                System.err.println("===");
                return false;
            }
        }

    }

    public static void poczatekGry(){

        switch (indexOfCurrentPlayer){
            case 0 -> {

                int yx = numeryPola(0);
                int x = yx%100;
                yx /=100;
                int y = yx;

                CzyZbic(y, x);

                pole[y][x] = "a  ";

                if (pole[1][10] == "a  "){
                    pole[1][10] = "   ";
                }else if (pole[1][11] == "a  "){
                    pole[1][11] = "   ";
                }else if (pole[2][10] == "a  "){
                    pole[2][10] = "   ";
                }else if (pole[2][11] == "a  "){
                    pole[2][11] = "   ";
                }else{

                    System.out.println("nie ma pionow");
                }



                show(pole);

            }
            case 1 ->{

                int yx = numeryPola(10);
                int x = yx % 100;
                yx /= 100;
                int y = yx;

                CzyZbic(y, x);

                pole[y][x] = "b  ";


                if (pole[10][10] == "b  "){
                    pole[10][10] = "   ";
                }else if (pole[10][11] == "b  "){
                    pole[10][11] = "   ";
                }else if (pole[11][10] == "b  "){
                    pole[11][10] = "   ";
                }else if (pole[11][11] == "b  "){
                    pole[11][11] = "   ";
                }else{

                    System.out.println("nie ma pionow");
                }

                show(pole);

            }
            case 2 ->{
                int yx = numeryPola(20);
                int x = yx % 100;
                yx /= 100;
                int y = yx;

                CzyZbic(y, x);

                pole[y][x] = "c  ";



                if (pole[10][1] == "c  "){
                    pole[10][1] = "   ";
                }else if (pole[10][2] == "c  "){
                    pole[10][2] = "   ";
                }else if (pole[11][1] == "c  "){
                    pole[11][1] = "   ";
                }else if (pole[11][2] == "c  "){
                    pole[11][2] = "   ";
                }else{

                    System.out.println("nie ma pionow");
                }
                show(pole);

            }
            case 3 ->{

                int yx = numeryPola(30);
                int x = yx % 100;
                yx /= 100;
                int y = yx;

                CzyZbic(y, x);

                pole[y][x] = "d  ";


                if (pole[1][1] == "d  "){
                    pole[1][1] = "   ";
                }else if (pole[1][2] == "d  "){
                    pole[1][2] = "   ";
                }else if (pole[2][1] == "d  "){
                    pole[2][1] = "   ";
                }else if (pole[2][2] == "d  "){
                    pole[2][2] = "   ";
                }else{
                    System.out.println("nie ma pionow");
                }
                show(pole);

            }
        }

        if (indexOfCurrentPlayer <(iloscGraczy-1)){
            indexOfCurrentPlayer++;
        }else
            indexOfCurrentPlayer=0;

    }

    public static void CzyZbic(int y , int x){
        if (pole[y][x] == "a  "){

            if (pole[1][10] == "   "){
                pole[1][10] = "a  ";
            }else if (pole[1][11] == "   "){
                pole[1][11] = "a  ";
            }else if (pole[2][10] == "   "){
                pole[2][10] = "a  ";
            }else if (pole[2][11] == "   "){
                pole[2][11] = "a  ";
            }

        }
        else if (pole[y][x] == "b  "){
            if (pole[10][10] == "   "){
                pole[10][10] = "b  ";
            }else if (pole[10][11] == "   "){
                pole[10][11] = "b  ";
            }else if (pole[11][10] == "   "){
                pole[11][10] = "b  ";
            }else if (pole[11][11] == "   "){
                pole[11][11] = "b  ";
            }
        }
        else if (pole[y][x] == "c  "){
            if (pole[10][1] == "   "){
                pole[10][1] = "c  ";
            }else if (pole[10][2] == "   "){
                pole[10][2] = "c  ";
            }else if (pole[11][1] == "   "){
                pole[11][1] = "c  ";
            }else if (pole[11][2] == "   "){
                pole[11][2] = "c  ";
            }
        }
        else if (pole[y][x] == "d  "){
            if (pole[1][1] == "   "){
                pole[1][1] = "d  ";
            }else if (pole[1][2] == "   "){
                pole[1][2] = "d  ";
            }else if (pole[2][1] == "   "){
                pole[2][1] = "d  ";
            }else if (pole[2][2] == "   "){
                pole[2][2] = "d  ";
            }
        }
    }

    public static void show(String[][] pole){
        for (int i = 0; i < pole.length; i++) {
            for (int j = 0; j < pole[i].length; j++) {
                System.out.print(pole[i][j]);
            }
            System.out.println();
        }
    }

    public static String[][] pole(){
        String[][] pole1 = new String[13][13];
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                if ((i >= 1 && i<=4) || (i >= 8 && i<=11)){
                    if (j < 5 || j== 6 || j>7){
                        pole1[i][j] = "   ";
                    }else{
                        pole1[i][j] = "x  ";
                    }
                }else if (i == 5 || i ==7){
                    if (j != 0 && j != 6 && j != 12){
                        pole1[i][j] = "x  ";
                    }else {
                        pole1[i][j] = "   ";
                    }
                }else
                    pole1[i][j] = "   ";
            }
        }

        pole1[1][6] = "x  ";
        pole1[11][6] = "x  ";
        pole1[6][1] = "x  ";
        pole1[6][11] = "x  ";

        pole1[0][7] = "0  ";
        pole1[5][0] = "30 ";
        pole1[12][5] = "20 ";
        pole1[7][12] = "10 ";

        pole1[1][10] = "a  ";
        pole1[1][11] = "a  ";
        pole1[2][10] = "a  ";
        pole1[2][11] = "a  ";

        pole1[1][1] = "d  ";
        pole1[1][2] = "d  ";
        pole1[2][1] = "d  ";
        pole1[2][2] = "d  ";

        pole1[10][1] = "c  ";
        pole1[10][2] = "c  ";
        pole1[11][1] = "c  ";
        pole1[11][2] = "c  ";

        pole1[10][10] = "b  ";
        pole1[10][11] = "b  ";
        pole1[11][10] = "b  ";
        pole1[11][11] = "b  ";

        pole = pole1;

        return pole;

    }

    public static int numeryPola(int i){
        int[] tab = new int[]{
                107, 207 , 307, 407, 507, 508 , 509 , 510 , 511, 611, 711 ,
                710, 709 , 708 , 707 , 807 , 907, 1007 , 1107 , 1106 , 1105,
                1005 , 905 , 805 , 705 , 704 , 703 , 702 , 701 , 601 , 501,
                502 , 503 , 504 , 505 , 405, 305 , 205 , 105 , 106

        };
        return tab[i];
    }
}