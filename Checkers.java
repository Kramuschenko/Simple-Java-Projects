public
    class Checkers {

    public static void main(String[] args) {

        // startowe pozycji

        long P_Czarne_1_6 = 0b100000001_100000011_100000101_100000111_100001000_100001010L;
        long P_Czarne_7_12 = 0b100001100_100001110_100010001_100010011_100010101_100010111L;
        long P_Biale_1_6 = 0b101101000_101101010_101101100_101101110_101110001_101110011L;
        long P_Biale_7_12 = 0b101110101_101110111_101111000_101111010_101111100_101111110L;


        long P_Czarne_1_6New = 0b000000000;
        long P_Czarne_7_12New = 0b000000000;
        long P_Biale_1_6New = 0b000000000;
        long P_Biale_7_12New = 0b000000000;


        java.util.Scanner sc = new java.util.Scanner(System.in);

        System.out.println("Gra zacząla się!");
        System.out.println("Jezeli chcesz ją kończyć wprowadz \"11 11\"" + '\n');

        int OldX = 0 , OldY = 0 , NewX = 0 ,NewY = 0 , ZbitaX = 0, ZbitaY = 0;

        // petla gry

        while((OldX != 11 && OldY != 11) || (NewX != 11 && NewY != 11)){

            // stworzenie pola

            String line0 = "", line1 = "", line2 = "", line3 = "", line4 = "", line5 = "", line6 = "", line7 = "" ;


            for (int i = 0; i < 8; i++) {
                if (i % 2 ==0 ){
                    line0 = line0 + "" + '\u2B1B';
                    line2 = line2 + "" + '\u2B1B';
                    line4 = line4 + "" + '\u2B1B';
                    line6 = line6 + "" + '\u2B1B';
                    //============================
                    line1 = line1 + "" + '\u2B1C';
                    line3 = line3 + "" + '\u2B1C';
                    line5 = line5 + "" + '\u2B1C';
                    line7 = line7 + "" + '\u2B1C';
                }else{
                    line0 = line0 + "" + '\u2B1C';
                    line2 = line2 + "" + '\u2B1C';
                    line4 = line4 + "" + '\u2B1C';
                    line6 = line6 + "" + '\u2B1C';
                    //============================
                    line1 = line1 + "" + '\u2B1B';
                    line3 = line3 + "" + '\u2B1B';
                    line5 = line5 + "" + '\u2B1B';
                    line7 = line7 + "" + '\u2B1B';
                }
            }

            // copowanie pola dla zmiany

            long copy_CZ_1_6 = P_Czarne_1_6;
            long copy_CZ_7_12 = P_Czarne_7_12;
            long copy_BL_1_6 = P_Biale_1_6;
            long copy_BL_7_12 = P_Biale_7_12;



            for (int i = 0; i < 4; i++) {

                int tmp;
                int cases ;

                if (copy_CZ_1_6 != 0){
                    cases = 1;
                }
                else if (copy_CZ_7_12 != 0){
                    cases = 2;
                }
                else if (copy_BL_1_6 !=0){
                    cases = 3;
                }
                else{
                    cases = 4;
                }

                for (int j = 0; j < 6; j++) {

                    // dostawanie po 9 ostatnich bitow dla analizu

                    switch (cases) {
                        case 1 -> {
                            tmp = (int) (copy_CZ_1_6 & 0b111111111);
                            copy_CZ_1_6 = copy_CZ_1_6 >> 9;
                        }
                        case 2 -> {
                            tmp = (int) (copy_CZ_7_12 & 0b111111111);
                            copy_CZ_7_12 = copy_CZ_7_12 >> 9;
                        }
                        case 3 -> {
                            tmp = (int) (copy_BL_1_6 & 0b111111111);
                            copy_BL_1_6 = copy_BL_1_6 >> 9;
                        }
                        case 4 -> {
                            tmp = (int) (copy_BL_7_12 & 0b111111111);
                            copy_BL_7_12 = copy_BL_7_12 >> 9;
                        }
                        default -> tmp = 0;
                    }

                    // sprawdzanie czy jest w grze

                    boolean isInGame = ((tmp & 0b100000000) == 0b100000000);

                    // pozicyja piona

                    int YPosition = (tmp >> 3) & 0b111;
                    int XPosition = tmp & 0b000111;

                    // zbiwanie figur

                    if (YPosition == ZbitaY && XPosition == ZbitaX){
                        isInGame = false;

                        int NotPlaying;
                        NotPlaying = tmp & 0b011111111;

                        switch (cases) {
                            case 1 -> {
                                P_Czarne_1_6New = P_Czarne_1_6New << 9;
                                P_Czarne_1_6New = P_Czarne_1_6New | NotPlaying;
                            }
                            case 2 -> {
                                P_Czarne_7_12New = P_Czarne_7_12New << 9;
                                P_Czarne_7_12New = P_Czarne_7_12New | NotPlaying;
                            }
                            case 3 -> {
                                P_Biale_1_6New = P_Biale_1_6New << 9;
                                P_Biale_1_6New = P_Biale_1_6New | NotPlaying;
                            }
                            case 4 -> {
                                P_Biale_7_12New = P_Biale_7_12New << 9;
                                P_Biale_7_12New = P_Biale_7_12New | NotPlaying;
                            }
                            default -> tmp = 0;
                        }

                        // przesuniecie figury

                    }else if (YPosition == OldY && XPosition == OldX){

                        int copyOfY = OldY;

                        if (((tmp & 0b010000000) != 0b010000000) && (
                                (OldX-1 == NewX && OldY - 1 == NewY) ||
                                (OldX+1 == NewX && OldY - 1 == NewY) ||
                                (OldX-1 == NewX && OldY + 1 == NewY) ||
                                (OldX+1 == NewX && OldY + 1 == NewY))){
                            YPosition = NewY;
                            XPosition = NewX;

                            copyOfY = NewY;

                            tmp = tmp & 0b111000000;
                            NewY = NewY << 3;
                            tmp = tmp | NewY;
                            tmp = tmp | NewX;
                        } else if (((tmp & 0b010000000) != 0b010000000) && (
                                (OldX-2 == NewX && OldY - 2 == NewY) ||
                                (OldX+2 == NewX && OldY - 2 == NewY) ||
                                (OldX-2 == NewX && OldY + 2 == NewY) ||
                                (OldX+2 == NewX && OldY + 2 == NewY))){
                            YPosition = NewY;
                            XPosition = NewX;

                            copyOfY = NewY;

                            tmp = tmp & 0b111000000;
                            NewY = NewY << 3;
                            tmp = tmp | NewY;
                            tmp = tmp | NewX;
                        }
                        else if ((tmp & 0b010000000) == 0b010000000){
                            YPosition = NewY;
                            XPosition = NewX;

                            copyOfY = NewY;

                            tmp = tmp & 0b111000000;
                            NewY = NewY << 3;
                            tmp = tmp | NewY;
                            tmp = tmp | NewX;
                        }else{

                            tmp = tmp & 0b111000000;
                            OldY = OldY << 3;
                            tmp = tmp | OldY;
                            tmp = tmp | OldX;

                            System.out.println("Pion tak nie chodzi!");
                        }

                        // dodawanie figury do pamieci po chodzie

                        switch (cases) {
                            case 1 -> {
                                if (copyOfY == 7 && ((tmp & 0b010000000) != 0b010000000)){
                                    tmp = tmp | 0b010000000;
                                }

                                P_Czarne_1_6New = P_Czarne_1_6New << 9;
                                P_Czarne_1_6New = P_Czarne_1_6New | tmp;
                            }
                            case 2 -> {
                                if (copyOfY == 7 && ((tmp & 0b010000000) != 0b010000000)){
                                    tmp = tmp | 0b010000000;
                                }

                                P_Czarne_7_12New = P_Czarne_7_12New << 9;
                                P_Czarne_7_12New = P_Czarne_7_12New | tmp;
                            }
                            case 3 -> {
                                if (copyOfY == 0 && ((tmp & 0b010000000) != 0b010000000)){
                                    tmp = tmp | 0b010000000;
                                }

                                P_Biale_1_6New = P_Biale_1_6New << 9;
                                P_Biale_1_6New = P_Biale_1_6New | tmp;
                            }
                            case 4 -> {
                                if (copyOfY == 0 && ((tmp & 0b010000000) != 0b010000000)){
                                    tmp = tmp | 0b010000000;
                                }

                                P_Biale_7_12New = P_Biale_7_12New << 9;
                                P_Biale_7_12New = P_Biale_7_12New | tmp;
                            }
                            default -> tmp = 0;
                        }
                    }else{

                        // dodawanie figury do pamieci

                        switch (cases) {
                            case 1 -> {
                                P_Czarne_1_6New = P_Czarne_1_6New << 9;
                                P_Czarne_1_6New = P_Czarne_1_6New | tmp;
                            }
                            case 2 -> {
                                P_Czarne_7_12New = P_Czarne_7_12New << 9;
                                P_Czarne_7_12New = P_Czarne_7_12New | tmp;
                            }
                            case 3 -> {
                                P_Biale_1_6New = P_Biale_1_6New << 9;
                                P_Biale_1_6New = P_Biale_1_6New | tmp;
                            }
                            case 4 -> {
                                P_Biale_7_12New = P_Biale_7_12New << 9;
                                P_Biale_7_12New = P_Biale_7_12New | tmp;

                            }
                            default -> tmp = 0;
                        }
                    }

                    // dodawanie figury na pole

                    if (isInGame){

                        boolean isBialy = ((tmp & 0b101000000) == 0b101000000);
                        boolean isDamka = ((tmp & 0b110000000) == 0b110000000);

                        char figure;

                        if (isBialy && isDamka)
                            figure = '\u265B';
                        else if (!isBialy && isDamka)
                            figure = '\u2655';
                        else if (isBialy && !isDamka)
                            figure = '\u265F';
                        else if (!isBialy && !isDamka)
                            figure = '\u2659';
                        else
                            figure = ' ';


                        switch (YPosition){
                            case 0 -> {
                                String lineTMP = line0;
                                lineTMP = lineTMP.substring(0,XPosition) + figure + lineTMP.substring(XPosition+1);
                                line0 = lineTMP;
                            }
                            case 1 -> {
                                String lineTMP = line1;
                                lineTMP = lineTMP.substring(0,XPosition) + figure + lineTMP.substring(XPosition+1);
                                line1 = lineTMP;
                            }
                            case 2 -> {
                                String lineTMP = line2;
                                lineTMP = lineTMP.substring(0,XPosition) + figure + lineTMP.substring(XPosition+1);
                                line2 = lineTMP;
                            }
                            case 3 -> {
                                String lineTMP = line3;
                                lineTMP = lineTMP.substring(0,XPosition) + figure + lineTMP.substring(XPosition+1);
                                line3 = lineTMP;
                            }
                            case 4 -> {
                                String lineTMP = line4;
                                lineTMP = lineTMP.substring(0,XPosition) + figure + lineTMP.substring(XPosition+1);
                                line4 = lineTMP;
                            }
                            case 5 -> {
                                String lineTMP = line5;
                                lineTMP = lineTMP.substring(0,XPosition) + figure + lineTMP.substring(XPosition+1);
                                line5 = lineTMP;
                            }
                            case 6 -> {
                                String lineTMP = line6;
                                lineTMP = lineTMP.substring(0,XPosition) + figure + lineTMP.substring(XPosition+1);
                                line6 = lineTMP;
                            }
                            case 7 -> {
                                String lineTMP = line7;
                                lineTMP = lineTMP.substring(0,XPosition) + figure + lineTMP.substring(XPosition+1);
                                line7 = lineTMP;
                            }
                        }

                    }
                }

                // pamiec dla nastepnego ruchu

                P_Czarne_1_6 = P_Czarne_1_6New;
                P_Czarne_7_12 = P_Czarne_7_12New;
                P_Biale_1_6 = P_Biale_1_6New;
                P_Biale_7_12 = P_Biale_7_12New;

            }

            // odnowienie pamieci

            P_Czarne_1_6New &= 0b000000000;
            P_Czarne_7_12New &= 0b000000000;
            P_Biale_1_6New &= 0b000000000;
            P_Biale_7_12New &= 0b000000000;

            // wywolanie pola

            System.out.print("  ");
            for (int i = 0; i < 8; i++) {
                System.out.print(i+ " ");
            }
            System.out.println();
            System.out.println("0 " + line0);
            System.out.println("1 " + line1);
            System.out.println("2 " + line2);
            System.out.println("3 " + line3);
            System.out.println("4 " + line4);
            System.out.println("5 " + line5);
            System.out.println("6 " + line6);
            System.out.println("7 " + line7);


            // wprowadzanie liczb

            System.out.println("Wprowadz miejsce figury, którą chcesz zająć (Y X)");
            OldY = sc.nextInt();
            OldX = sc.nextInt();

            System.out.println("Wprowadz miejsce, w którym chcesz to umieścić (Y X)");
            NewY = sc.nextInt();
            NewX = sc.nextInt();

            System.out.println("Wprowadz miejsce zbitej figury (jezeli nie bylo zbitej figury - wprowadz \"12 12\") (Y X)");
            ZbitaY = sc.nextInt();
            ZbitaX = sc.nextInt();

        }
    }
}
