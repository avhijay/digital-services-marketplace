import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Person> people = getPeople();

        // how i used to do it
//
//        List<Person> myFemales = new ArrayList<>();
//
//        for (Person person: people){
//            if (person.getGender().equals(Gender.FEMALE)){
//                myFemales.add(person);
//            }
//        }
//        myFemales.forEach(System.out::println);

// how to actually do it (declarative approach)

// 1> Filter
        List<Person> myGirls = people.stream().filter(person -> person.getGender().equals(Gender.FEMALE))// filter takes a predicate which return true or false
                .collect(Collectors.toList()); // collect the result back to the list (new list)

//myGirls.forEach(System.out::println);




//2> SORT
        List<Person> byAge = people.stream().sorted(Comparator.comparing(Person::getAge))//sort takes in  comparator for field i want to compare
                .toList();

//        byAge.forEach(System.out::println);




//3> All Match   --> checks for all list  as the specified condition and return boolean accordingly
        Boolean allAge = people.stream().allMatch(person -> person.getAge() > 5); // takes a predicate and return as boolean
//        System.out.println(allAge);


        // AnyMatch

        Boolean anyMatchAge =people.stream().anyMatch(person -> person.getAge()>60); // checks for any match as the specified condition and return boolean accordingly
//      System.out.println(anyMatchAge);




// 4> NoneMatch
       boolean noneMtch = people.stream().noneMatch(person -> person.getName().equals("Mark"));

        System.out.println(noneMtch);




        // 5> Max

//        Optional<Person> max = can do it this way too
     people.stream().max(Comparator.comparing(Person::getAge)).ifPresent(System.out::println);   //   also takes a comparator  , uses optional as if the particular is not present has to return nothing , but can also be done by .if present





// 6> MIN    -> returns minimum   (Person::getAge  is a method reference==>  refer to an existing method so that it can be used where a lambda is expected )
        people.stream().min(Comparator.comparing(Person::getAge)).ifPresent(System.out::println);




// 7> GROUP --> group data based on the fields we have

        // produces map , as multiple elements share a common  key


        //Takes a stream of elements and reorganizes them into buckets, where each bucket is identified by a key derived from each element.

        Map<Gender, List<Person>> groupByGender = people.stream().collect(Collectors.groupingBy(Person::getGender));
        groupByGender.forEach((gender, people1) -> {

            System.out.println(gender);
            people1.forEach(System.out::println);
            System.out.println();
        });

        Optional<String> oldGirl = people.stream().filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge)).map(Person::getName);

        oldGirl.ifPresent(System.out::println);
    }

    private static List<Person> getPeople() {

        return List.of(
                new Person("Name1",18,Gender.MALE),
        new Person("Name2",15,Gender.FEMALE),
                new Person("NAME 3 ",67,Gender.FEMALE),
                new Person("Name6",45,Gender.MALE)

        );

    }
}
