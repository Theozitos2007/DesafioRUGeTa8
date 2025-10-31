import java.util.Scanner;

//todo: last thing to fix IF YOU CAN is make sure nextInt is nextline with validity check
public class ExecutablePartRU {
    static Scanner readGuy = new Scanner(System.in);


    public static void foodCreateMain(){
        System.out.println("prosseguindo para criar uma refeição");
        //create new food code block
        boolean validDish = false;
        String dishName = null;
        String inDishType = null;

        do { // will only exit input gather loop once dish is valid
            System.out.println("digite o nome do prato:");
            dishName = readGuy.nextLine();

            System.out.println("digite o tipo do prato (SEM ACENTOS) -- \"principal\" \"acompanhamento\" ou \"salada\"");
            inDishType = readGuy.nextLine();

            validDish = MethodsRU.foodValidity(dishName, inDishType);
        }while (!validDish);

        // can move on to add dish once its past the loop

        MethodsRU.foodCreate(dishName, SharedVariablesRU.dishType, validDish);

        System.out.println("lista de pratos finalizada como:");
        MethodsRU.foodListPrint();

        System.out.println("\n\n1:fechar programa\n2:de volta ao início\n3:criar outro prato");
        MethodsRU.looperGeneral(true);
    }

    public static void foodDeleteMain(){
        System.out.println("prosseguindo para apagar um prato");

        if(SharedVariablesRU.foodList.isEmpty()){
            System.out.println("falhou em prosseguir: não há pratos registrados. por favor, adicione um prato primeiro.");
        }else{
            MethodsRU.foodListPrint();
            System.out.println("digite o índice do prato para remove-lo.");
            int foodToRemove = readGuy.nextInt();
            readGuy.nextLine();
            MethodsRU.foodDelete(foodToRemove);
        }

        System.out.println("\n\n1:fechar programa\n2:de volta ao início\n3:apagar outro prato.");
        MethodsRU.looperGeneral(true);
    }

    public static void mealCreateMain(){
        System.out.println("prosseguindo para criar uma refeição.");
// meal create
        boolean hasEnoughMain = false;
        boolean hasEnoughSide = false;
        boolean hasEnoughSalad = false;

        for(int i = 0; i < SharedVariablesRU.foodList.size(); i++) {
            if(SharedVariablesRU.foodList.get(i).type == 1){
                hasEnoughMain = true;
            }else if(SharedVariablesRU.foodList.get(i).type == 2){
                hasEnoughSide = true;
            }else if(SharedVariablesRU.foodList.get(i).type == 3){
                hasEnoughSalad = true;
            }
        }

        if(hasEnoughMain && hasEnoughSide && hasEnoughSalad){
            // can continue to make a meal
            boolean nullValidity = false; // null validity will be true if it isn't null

            do {
                System.out.println("a atual lista de pratos:");
                MethodsRU.foodListPrint();
                System.out.println("para criar uma refeição, digite o índice correspondente ao prato que quer adicionar a ela.");
                System.out.println("digite o índice do prato principal:");
                int foodIndexMain = readGuy.nextInt();
                readGuy.nextLine();
                System.out.println("digite o índice do acompanhamento:");
                int foodIndexSide = readGuy.nextInt();
                readGuy.nextLine();
                System.out.println("digite o índice da salada:");
                int foodIndexSalad = readGuy.nextInt();
                readGuy.nextLine();

                if(MethodsRU.mealValidity(
                        SharedVariablesRU.foodList.get(foodIndexMain),
                        SharedVariablesRU.foodList.get(foodIndexSide),
                        SharedVariablesRU.foodList.get(foodIndexSalad))){
                    // if the foods are NOT null
                    MethodsRU.mealCreate(
                            SharedVariablesRU.foodList.get(foodIndexMain),
                            SharedVariablesRU.foodList.get(foodIndexSide),
                            SharedVariablesRU.foodList.get(foodIndexSalad));
                    nullValidity = true;
                }
            } while(!nullValidity);
            // loop stops once its checked that theyre not null & added the meal

            System.out.println("lista de refeições finalizada como:");
            MethodsRU.mealListPrint();

            //

        }else{
            System.out.println("falhou em prosseguir: precisa de uma refeição de cada tipo registrada para criar refeição.");
            if(!hasEnoughMain){
                System.out.println("falta um prato principal.");
            }if(!hasEnoughSide){
                System.out.println("falta um acompanhamento");
            }if(!hasEnoughSalad){
                System.out.println("falta uma salada.");
            }
        }

        System.out.println("\n\n1:fechar programa\n2:de volta ao início\n3:criar outra refeição");
        MethodsRU.looperGeneral(true);
    }

    public static void mealDeleteMain(){
        System.out.println("prosseguindo para apagar uma refeição.");
        if(SharedVariablesRU.mealList.isEmpty()){
            System.out.println("falhou em prosseguir: não há refeições registradas.");

        }else{
            MethodsRU.mealListPrint();
            System.out.println("digite o índice da refeição para remove-la.");

            int mealToRemove = readGuy.nextInt();
            readGuy.nextLine();
            MethodsRU.mealDelete(mealToRemove);
        }

        System.out.println("\n\n1:fechar programa\n2:de volta ao início\n3:apagar outra refeição");
        MethodsRU.looperGeneral(true);
    }

    public static void weekMenuCreateMain(){
        System.out.println("prosseguindo para criação do cardápio semanal");
        // check if there are enough
        switch(SharedVariablesRU.mealList.size()){
            case 0:
                System.out.println("falhou na criação do cardápio semanal: não há refeições suficiente. precisa: 1 ou mais refeições registradas. tem: 0 refeições registradas.");
                break;
            case 1,2,3,4,5,6,7,8,9,10,11,12,13:
                System.out.println("não há refeições o suficiente registradas para que cada turno tenha uma refeição única. precisa: 14 ou mais refeições registradas. tem: " + SharedVariablesRU.mealList.size());
                System.out.println("isso significa que os turnos terão refeições repetidas. prosseguir mesmo assim? (S/N)");
                String proceedOrNot = readGuy.nextLine();

                boolean ynselect = false;
                boolean breakSwitch = false;
                while(!ynselect){
                    if (proceedOrNot.trim().equalsIgnoreCase("s")){
                        ynselect = true;
                    } else if (!proceedOrNot.trim().equalsIgnoreCase("n")) {
                        System.out.println("seleção inválida. tente novamente.");
                    }else{
                        breakSwitch = true;
                        ynselect = true;
                    }
                }

                if(breakSwitch){break;}

            default:
                //can proceed to week creation
                MethodsRU.weekMenuMaker();

        }

        System.out.println("\n\n1:fechar programa\n2:de volta ao início\n3:tentar novamente");
        MethodsRU.looperGeneral(true);
    }

    public static void weekMenuPrintMain(){
        System.out.println("prosseguindo para imprimir cardápio semanal.");

        // check if can be printed
        String unsetValues = null;
        boolean hasUnset = true;

        // i can only pray that this 2d array iterator works. it hurts my head//
        for (int i = 0; i < SharedVariablesRU.weekMenu.length; i++){
            for (int j = 0; j < SharedVariablesRU.weekMenu[i].length; j++){
                if(SharedVariablesRU.weekMenu[i][j] < 0 ){
                    if(i != 0 && j != 0){
                        unsetValues = i + "/" + j ;
                    }else {
                        unsetValues = unsetValues + "/" + i + "/" + j ;
                    }
                    //break;
                }
            }
        }

        try{
            if(unsetValues.trim().isEmpty()){
                hasUnset = false;
            }
        }catch(NullPointerException npe){
            hasUnset = false;
        }


        if(hasUnset){
            String[] unVaArray =  unsetValues.split("/");
            int iPlus;

            System.out.println("falhou em prosseguir: todos os turnos precisam de uma refeição registrada para eles.");
            System.out.println("falta registrar refeições para:");
            for (int i = 0; i < unVaArray.length; i++) {
                if (i % 2 == 0){ // if i is even, get i and i + 1. dont use unary operator that will increment it.  or you could but that´d need to be accounted 4
                    // i is day j is time, i think
                    iPlus = i + 1;

                    System.out.printf("Dia: %s, Turno: %s", MethodsRU.dayTimeStringCorrector(i, true), MethodsRU.dayTimeStringCorrector(iPlus, false));
                }
            }

        }else{
            //can print the menu
            MethodsRU.weekPrint();
        }

        System.out.println("\n\n1:fechar programa\n2:de volta ao início");
        MethodsRU.looperGeneral(false);
    }

    public static void helpText(){
        System.out.println("AJUDA E EXPLICAÇÃO.");
/*
        FoodRU debug1 = new FoodRU("arroz", 1);
        FoodRU debug2 = new FoodRU("hambirher", 1);
        FoodRU debug3 = new FoodRU("macarrao", 1);
        FoodRU debug4 = new FoodRU("farofa", 2);
        FoodRU debug5 = new FoodRU("pure", 2);
        FoodRU debug6 = new FoodRU("carne", 2);
        FoodRU debug7 = new FoodRU("tomate", 3);
        FoodRU debug8 = new FoodRU("alface", 3);
        FoodRU debug9 = new FoodRU("batata", 3);

        MealRU debugM1 = new MealRU(debug1, debug4, debug7);
        MealRU debugM2 = new MealRU(debug2, debug5, debug8);
        MealRU debugM3 = new MealRU(debug3, debug6, debug9);

        SharedVariablesRU.foodList.add(debug1);
        SharedVariablesRU.foodList.add(debug2);
        SharedVariablesRU.foodList.add(debug3);
        SharedVariablesRU.foodList.add(debug4);
        SharedVariablesRU.foodList.add(debug5);
        SharedVariablesRU.foodList.add(debug6);
        SharedVariablesRU.foodList.add(debug7);
        SharedVariablesRU.foodList.add(debug8);
        SharedVariablesRU.foodList.add(debug9);

        SharedVariablesRU.mealList.add(debugM1);
        SharedVariablesRU.mealList.add(debugM2);
        SharedVariablesRU.mealList.add(debugM3);

        System.out.println("done");
        MethodsRU.foodListPrint();
        MethodsRU.mealListPrint();*/
        System.out.println("esse programa consiste em criar um cardápio semanal para um restaurante universitário. ele permite:");
        System.out.println("ADICIONAR E REMOVER PRATOS.\num prato se refere a: um prato principal, um acompanhamento, ou uma salada.\npor exemplo, um arroz com carne pode ser prato principal, com acompanhamento de pure de batata, e cozido de legumes como salada.");
        System.out.println("ADICIONAR E REMOVER REFEIÇÕES.\nrefeição se refere a união de um prato principal, acompanhamento, e salada.");
        System.out.println("CRIAR E IMPRIMIR O CARDÁPIO SEMANAL.\napós associar uma refeição a cada turno da semana, pode se imprimir o cardápio.");
        System.out.println("ordem recomendada: criar 1 prato de cada tipo (no mínimo), criar 1 refeição, criar cardápio e finalmente imprimi-lo.");

        System.out.println("\n\n1:fechar programa\n2:de volta ao início");
        MethodsRU.looperGeneral(false);
    }

    public static void main(String[] args) {
        /*
        **Desafio RU:** Implemente em Java um cardápio semanal do RU.

- O cardápio do RU possui para cada dia e turno, uma refeição associada.
* A refeição possui uma salada, um prato principal e um acompanhamento. Cada um desses três são alimentos.
- O sistema deve permitir cadastrar os alimentos.
- Em seguida associar alimentos com refeição para montar refeições.
- Com as refeições instanciadas, permitir criar o Menu que é a associação de Dia, Turno e Refeição.
- E por fim, deixar o usuário criar os Menus de cada dia da semana.
* No final de tudo, você deve imprimir o cardápio semanal na tela, da melhor maneira possível.

components of meal - food.salad, food.main dish and food.side dish
menu params: day, turno and meal

* food can be an object: string param to type the food. value of if its salad main or side.
* meal can be an object. point to one salad one main one side.
* storer of food can be a set or arraylist
* daily menu can be an object pointing to one meal obj + has a day otw and turno value
* alternatively. week menu can be a matrix? but it would require too many checks
* week storer can be a size 7 arraylist of dailymenu obj? no need 4 month accounting because it didnt ask for it

* functionality: register food, create new meal, create menu for day, assemble for week and print
         */

        while(!SharedVariablesRU.globalQuitTime){
            boolean isValidSelection;
            int modeSelectInt = 0;
            System.out.println("selecione a função que quer utilizar.");
            System.out.println("1: registrar prato\n2: deletar prato\n3: registrar refeição\n4: deletar refeição\n5: criar cardápio semanal\n6: imprimir cardápio semanal\n7: ajuda e explicação");
            String modeSelectInput = readGuy.nextLine();

            try{
                modeSelectInt = Integer.parseInt(modeSelectInput.trim());
                isValidSelection = modeSelectInt > 0 && modeSelectInt < 8;

            }catch (NullPointerException | NumberFormatException e){
                isValidSelection = false;
            }

            if(isValidSelection){
                switch(modeSelectInt){
                    case 1:
                        while (!SharedVariablesRU.localQuitTime) {
                            foodCreateMain();
                        }
                        SharedVariablesRU.localQuitTime = false;
                        break;
                    case 2:
                        while (!SharedVariablesRU.localQuitTime) {
                            foodDeleteMain();
                        }
                        SharedVariablesRU.localQuitTime = false;
                        break;
                    case 3:
                        while (!SharedVariablesRU.localQuitTime) {
                            mealCreateMain();
                        }
                        SharedVariablesRU.localQuitTime = false;
                        break;
                    case 4:
                        while (!SharedVariablesRU.localQuitTime) {
                            mealDeleteMain();
                        }
                        SharedVariablesRU.localQuitTime = false;
                        break;
                    case 5:
                        while (!SharedVariablesRU.localQuitTime) {
                            weekMenuCreateMain();
                        }
                        SharedVariablesRU.localQuitTime = false;
                        break;
                    case 6:
                        weekMenuPrintMain();
                        break;
                    case 7:
                        helpText();
                        break;
                    default:
                        System.out.println("seleção inválida. tente novamente.");
                }
            }else{
                System.out.println("seleção inválida. tente novamente.");
            }
        }


    }
}
