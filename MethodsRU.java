import java.util.ArrayList;
import java.util.Scanner;

public class MethodsRU {

    SharedVariablesRU shvaObj = new SharedVariablesRU();

    //to make my life easier, individually print the integer as smth else
    public static String foodTypePrintIndividual(FoodRU inFood){
        return switch (inFood.type) {
            case 0 -> "VALOR INVALIDO";
            case 1 -> "prato principal";
            case 2 -> "acompanhamento";
            case 3 -> "salada";
            default -> Integer.toString(inFood.type);
        };
    }

    public static String mealPrintIndividual(MealRU inMeal){
        return String.format("(prato principal: %s | acompanhamento: %s | salada: %s)",
                inMeal.main.name, //name of main
                inMeal.side.name, //name of side
                inMeal.salad.name //name of salad
        );
    }

    //method to print foodlist
    public static void foodListPrint(){

        if(!SharedVariablesRU.foodList.isEmpty()){
            System.out.print("[");
            for(int i = 0; i < SharedVariablesRU.foodList.size(); i++) {
                System.out.printf("índice: %d, comida: %s, tipo do prato: %s",
                        i, //index
                        SharedVariablesRU.foodList.get(i).name, // name
                        foodTypePrintIndividual(SharedVariablesRU.foodList.get(i))); // type formated to string

                if(SharedVariablesRU.foodList.get(i) != SharedVariablesRU.foodList.getLast()){
                    System.out.print("\n");
                }
            }
            System.out.print("]\n");
        }else{
            System.out.println("[NÃO HÁ PRATOS REGISTRADOS]");
        }
    }

    //method to print arraylist
    public static void mealListPrint() {

        if(!SharedVariablesRU.mealList.isEmpty()){
            System.out.print("[");
            for(int i = 0; i < SharedVariablesRU.mealList.size(); i++) {
                System.out.printf("índice: %d -> %s",
                        i, //index
                        mealPrintIndividual(SharedVariablesRU.mealList.get(i)) //name of main side salad

                );
                if(SharedVariablesRU.mealList.get(i) != SharedVariablesRU.mealList.getLast()){
                    System.out.print("\n");
                }
            }
            System.out.println("]");

        }else {
        System.out.println("[NÃO HÁ REFEIÇÕES REGISTRADAS]");
        }
    }

    // validity checker of meal
    public static boolean mealValidity(FoodRU main, FoodRU side, FoodRU salad){
        // nullhandler
        boolean hasMain = main != null;
        boolean hasSide = side != null;
        boolean hasSalad = salad != null;
        boolean nullValidity = true;

        if(!hasMain){
            System.out.println("falhou em criar refeição: por favor, registre uma comida existente como prato principal.");
            nullValidity = false;
        }else if(!hasSide){
            System.out.println("falhou em criar refeição: por favor, registre uma comida existente como acompanhamento.");
            nullValidity = false;
        }else if(!hasSalad){
            System.out.println("falhou em criar refeição: por favor, registre uma comida existente como salada.");
            nullValidity = false;
        }

        if(nullValidity){
            if(main.type != 1){
                System.out.println("falhou em criar refeição: programa pediu: prato principal, usuário enviou: " + foodTypePrintIndividual(main));
                nullValidity = false;
            }if(side.type != 2){
                System.out.println("falhou em criar refeição: programa pediu: acompanhamento, usuário enviou: " + foodTypePrintIndividual(side));
                nullValidity = false;
            }
            if(salad.type != 3){
                System.out.println("falhou em criar refeição: programa pediu: salada, usuário enviou: " + foodTypePrintIndividual(salad));
                nullValidity = false;
            }
        }

        return nullValidity;
    }

    //create meal
    public static void mealCreate(FoodRU main, FoodRU side, FoodRU salad){
        boolean duplicityValidity = true;
        //since its instancing a new mealobj everytime it goes to add one, we shouldnt have the same issue as itemslista?
        MealRU mealToAdd = new MealRU(main, side, salad);

        // catch same obj
        for (int i = 0; i < SharedVariablesRU.mealList.size(); i++) {
            // if content of tempobj matches content of object selected, return true
            if(SharedVariablesRU.mealList.contains(mealToAdd)){
                System.out.println("falhou em criar refeição: refeição idêntica já existe.");
                duplicityValidity = false;
                break;
            }
        }
        if(duplicityValidity){
            SharedVariablesRU.mealList.add(mealToAdd);
            System.out.println("refeição adicionada com sucesso.");
        }
    }

    // validity checker of food
    public static boolean foodValidity(String name, String type){
        boolean nullValidity =  name != null
                && !name.isEmpty();

        if (type.trim().equalsIgnoreCase("principal")){
            SharedVariablesRU.dishType = 1;
        }else if (type.trim().equalsIgnoreCase("acompanhamento")){
            SharedVariablesRU.dishType = 2;
        }else if (type.trim().equalsIgnoreCase("salada")){
            SharedVariablesRU.dishType = 3;
        }else{
            SharedVariablesRU.dishType = 0;
        }

        if(nullValidity && SharedVariablesRU.dishType != 0){
            return true;
        }else if (!nullValidity){
            System.out.println("falhou em criar prato: por favor, digite a comida. ex: carne, arroz, tomate cereja.");
            return false;
        } else if (SharedVariablesRU.dishType == 0) {
            System.out.println("falhou em criar prato: por favor, digite o tipo corretamente. precisa ser \"principal\", \"acompanhamento\", ou \"salada\", sem acentos.");
            return false;
        }
        return false;
    }

    //create food
    public static void foodCreate(String inName, int inType , boolean nullValidity){
        if(nullValidity){
            FoodRU foodToAdd = new FoodRU(inName, inType);
            boolean alreadyExists = SharedVariablesRU.foodList.contains(foodToAdd);

            if(!alreadyExists){
                SharedVariablesRU.foodList.add(foodToAdd);
                System.out.println("prato adicionado com sucesso.");
            }else{
                System.out.println("falhou em criar prato: prato idêntico já existe.");
            }
        }
    }

    //delete food
    public static void foodDelete(int indexToDelete){
        //null checker
        boolean nullValidity;

        try {
            nullValidity = SharedVariablesRU.foodList.get(indexToDelete) != null
                    && !SharedVariablesRU.foodList.get(indexToDelete).name.isEmpty(); //should return false if either food points to an item that doesnt exist or the name param is empty
        } catch (NullPointerException | IndexOutOfBoundsException npe) {
            nullValidity = false;
        }

        if (!nullValidity){
            System.out.println("falhou em apagar prato: prato selecionado não existe.");
        }else{
            //check if there are meals with this food. delete them as well
            ArrayList<MealRU> mealsWithThisFood = new ArrayList<>();
            MealRU mealToRemove;

            boolean okToDelete = true;
            for (int i = 0; i < SharedVariablesRU.mealList.size(); i++) {
                if (SharedVariablesRU.mealList.get(i).main.equals(SharedVariablesRU.foodList.get(indexToDelete))  //if this meal's main matches this food OR
                    || SharedVariablesRU.mealList.get(i).side.equals(SharedVariablesRU.foodList.get(indexToDelete)) //if this meal's side matches this food OR
                    || SharedVariablesRU.mealList.get(i).salad.equals(SharedVariablesRU.foodList.get(indexToDelete))){ //if this meal's salad matches this food

                    mealToRemove = new MealRU(SharedVariablesRU.mealList.get(i).main,
                            SharedVariablesRU.mealList.get(i).side,
                            SharedVariablesRU.mealList.get(i).salad);

                    mealsWithThisFood.add(mealToRemove);
                }
            }

            if(!mealsWithThisFood.isEmpty()){
                System.out.println("este prato está presente em " + mealsWithThisFood.size() + " refeições. elas tambem serão apagadas. prosseguir? (S/N)");
                okToDelete = looperYN();
            }

            if(okToDelete){//integer doesnt work for this because the list size will update if its multiple and can result in oob exception. has to remove via object
                if(!mealsWithThisFood.isEmpty()){
                    SharedVariablesRU.mealList.removeAll(mealsWithThisFood);
                }
                SharedVariablesRU.foodList.remove(indexToDelete);
                System.out.println("prato apagado com sucesso.");
            }else{
                System.out.println("prato não apagado devido a comando do usuário.");
            }
        }
    }
    //delete meal
    public static void mealDelete(int indexToDelete){
        //null checker
        boolean nullValidity;

        try {
            MealRU mealToDelete = SharedVariablesRU.mealList.get(indexToDelete);
            nullValidity = mealToDelete != null;
            //should return false if meal points to an item that doesnt exist
        } catch (NullPointerException npe) {
            nullValidity = false;
        }

// if any food param is empty, thats weird
        if (!nullValidity){
            System.out.println("falhou em apagar refeição: refeição selecionada não existe.");
        }else{
            SharedVariablesRU.mealList.remove(indexToDelete);
            System.out.println("refeição apagada com sucesso.");
        }
    }

    //week menu maker
    public static void weekMenuMaker(){

        System.out.println("toda semana tem sete dias: você precisa associar uma refeição a cada turno(manhã e tarde) de cada dia. \nlista de refeições:");
        MethodsRU.mealListPrint();
        System.out.println("iniciando a associação de refeições.");
        //
        questionLooperWMM(0,0); // sunday morning
        System.out.println("próximo.");
        questionLooperWMM(0,1); // sunday evening
        System.out.println("próximo.");
        questionLooperWMM(1,0); // monday morning
        System.out.println("próximo.");
        questionLooperWMM(1,1); // monday evening
        System.out.println("próximo.");
        questionLooperWMM(2,0); // tuesday morning
        System.out.println("próximo.");
        questionLooperWMM(2,1); // tuesday evening
        System.out.println("próximo.");
        questionLooperWMM(3,0); // wednesday morning
        System.out.println("próximo.");
        questionLooperWMM(3,1); // wednesday evening
        System.out.println("próximo.");
        questionLooperWMM(4,0); // thursday morning
        System.out.println("próximo.");
        questionLooperWMM(4,1); // thursday evening
        System.out.println("próximo.");
        questionLooperWMM(5,0); // friday morning
        System.out.println("próximo.");
        questionLooperWMM(5,1); // friday evening
        System.out.println("próximo.");
        questionLooperWMM(6,0); // saturday morning
        System.out.println("próximo.");
        questionLooperWMM(6,1); // saturday evening
        //
        System.out.println("associação de refeições da semana concluida.");
    }

    // null handler for week menu assembly
    public static boolean mealNullHandlerWMM(String input){
        try {
            int indexInput = Integer.parseInt(input);
            MealRU nullTestObj = new MealRU(null, null, null);
            nullTestObj = SharedVariablesRU.mealList.get(indexInput);
            String nullTestStr = SharedVariablesRU.mealList.get(indexInput).main.name;
            boolean nullValidity = nullTestStr.isEmpty() || nullTestObj.equals(null);
            return !nullValidity;
        }catch (NullPointerException | IndexOutOfBoundsException npe){
            return false;
        }
    }

    // properly assemble strings based on int
    public static String dayTimeStringCorrector(int input, boolean isDay){
        String output = "nothing null nada";
        if(!isDay) {
            if (input == 0) {
                output = "MANHÃ";
            } else if (input == 1) {
                output = "TARDE";
            } else {
                output = "VALOR INVALIDO";
            }
        }else {
            return switch (input) {
                case 0 -> "DOMINGO";
                case 1 -> "SEGUNDA";
                case 2 -> "TERÇA";
                case 3 -> "QUARTA";
                case 4 -> "QUINTA";
                case 5 -> "SEXTA";
                case 6 -> "SABADO";
                default -> "VALOR INVALIDO";
            };
        }
        return output;
    }

    //method that loops questions for the week menu maker
    public static void questionLooperWMM(int inDay, int inMorningOrEvening){
        Scanner readGuyQLWMM = new Scanner(System.in);
        
        // properly assemble strings based on int
        String morningOrEvening = dayTimeStringCorrector(inMorningOrEvening, false);
        String day = dayTimeStringCorrector(inDay, true);

        boolean methodLocalQuitTime = false;

        do{
            System.out.println("por favor, digite índice do dia da semana: " + day + ", no turno: " + morningOrEvening);
            String indexInput = readGuyQLWMM.nextLine();

            if(indexInput.isEmpty()){
                System.out.println("falhou. por favor, digite algo.");
            } else if (!mealNullHandlerWMM(indexInput)) { // else if index of item user pointed to doesnt exist)
                System.out.println("falhou. essa refeição não existe -- por favor, digite um índice válido.");
            }else{
                System.out.println("prestes a adicionar refeição de índice " + indexInput + " como refeição do turno: " + day + " de " + morningOrEvening +  ":\n" + mealPrintIndividual(SharedVariablesRU.mealList.get(Integer.parseInt(indexInput))));
                System.out.println("pode prosseguir? (S/N)");

                    boolean saidYes = looperYN();


                    if(saidYes){
                        // finally can add this in
                        SharedVariablesRU.weekMenu[inDay][inMorningOrEvening] = Integer.parseInt(indexInput);
                        System.out.println("refeição adicionada com sucesso.");
                        methodLocalQuitTime = true;
                    }else{
                        System.out.println("refeição não adicionada devido a comando do usuário. voltando a seleção.");
                    }
            }
        }
        while (!methodLocalQuitTime);
    }

    // method to print weekly menu.
    public static void weekPrint(){
        // i can only pray that this 2d array iterator works. it hurts my head

        System.out.println("----------------------------{CARDÁPIO SEMANAL}----------------------------");

        for (int i = 0; i < SharedVariablesRU.weekMenu.length; i++){
            System.out.println("----------<" + dayTimeStringCorrector(i, true) + ">----------");
            for (int j = 0; j < SharedVariablesRU.weekMenu[i].length; j++){
                // i is day, j is time
                System.out.printf("%s: %s%n",
                        dayTimeStringCorrector(j, false), // PRINT MORNING OR EVENING//METHOD TO PRINT INDIVIDYUAL MEAL
                        mealPrintIndividual(SharedVariablesRU.mealList.get( // converts mealobj into string
                                SharedVariablesRU.weekMenu[i][j] // get the meal the weekmenu pointed as being the times meal
                        )));

            }
        }

        System.out.println("-------------------------------------------------------------------------");

    }

    // general confirmation looper
    // 1:fechar programa 2:de volta ao início 3: repita a funcao
    public static void looperGeneral(boolean hasRepeat){
        Scanner readGuyGQT = new Scanner(System.in);
        boolean isValidSelection;
        boolean methodQuitTime = false;
        int modeSelectInt = 0;

        while(!methodQuitTime){
            String modeSelectInput = readGuyGQT.nextLine();
            try{
                modeSelectInt = Integer.parseInt(modeSelectInput.trim());
                isValidSelection = modeSelectInt > 0 && modeSelectInt < 8;

            }catch (NullPointerException | NumberFormatException e){
                isValidSelection = false;
            }

            if(isValidSelection && modeSelectInt == 1){
                SharedVariablesRU.globalQuitTime = true;
                methodQuitTime = true;
            }else if(isValidSelection && modeSelectInt == 2){
                SharedVariablesRU.localQuitTime = true;
                methodQuitTime = true;
            } else if (isValidSelection && hasRepeat && modeSelectInt == 3) {
                methodQuitTime = true;
            }else{
                System.out.println("seleção inválida. tente novamente.");
            }
        }
    }

    //yn looper specifically
    // related code block
    /*
    if(saidYes){
        user said yes. react accordingly
    }else{
        user said no. react accordingly
    }
     */
    public static boolean looperYN(){
        Scanner readGuyYN = new Scanner(System.in);

        String proceedOrNot = readGuyYN.nextLine();

        boolean ynselect = false;
        while(!ynselect){
            if (proceedOrNot.trim().equalsIgnoreCase("s")){
                return true;
            } else if (!proceedOrNot.trim().equalsIgnoreCase("n")) {
                System.out.println("seleção inválida. tente novamente.");
                proceedOrNot = readGuyYN.nextLine();
            }else{ // if said "n"
                return false;
            }
        }
        return false; //it shouldnt ever get here, but the program needs it
    }

    public static void main(String[] args) {
    }
}
