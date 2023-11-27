import java.util.ArrayList;

public class Pond {
    public static void main (String[] args) {
        ArrayList<Frog> listFrogs = new ArrayList<>();
        Frog frog1 = new Frog("Noe", 10, 10);
        Frog frog2 = new Frog("Benj", 10, 10);
        listFrogs.add(frog1);
        listFrogs.add(frog2);

        ArrayList<Fly> listFlys = new ArrayList<>();
        Fly fly1 = new Fly("Baptiste", 10, 10);
        listFlys.add(fly1);

        String string = new String();
        for (Frog f : listFrogs){
            string += " la grenouille " + f.name;
        }
        for (Fly f : listFlys){
            string += " la mouche " + f.name;
        }
        System.out.println("Un étang avec " + string);
        System.out.println(frog1.eat(fly1));

    }
}
