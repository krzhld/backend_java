package edu.hw4;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnimalOperations {
    private AnimalOperations() {

    }

    private static final int ONE_METER_IN_CM = 100;

    public static List<Animal> getByHeightAsc(List<Animal> animals) {
        return animals
            .stream()
            .sorted(Comparator.comparing(Animal::height))
            .toList();
    }

    public static List<Animal> getKFirstByWeightDesc(List<Animal> animals, int k) {
        return animals
            .stream()
            .sorted(Comparator.comparing(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    public static Map<Animal.Type, Long> countAnimalsByType(List<Animal> animals) {
        return animals
            .stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    public static Animal getAnimalWithLongestName(List<Animal> animals) {
        return animals
            .stream()
            .max(Comparator.comparing(animal -> animal.name().length()))
            .orElse(null);
    }

    public static Animal.Sex getMostSex(List<Animal> animals) {
        return animals
            .stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .orElse(null)
            .getKey();
    }

    public static Map<Animal.Type, Animal> getMostHeavyAnimalsEachType(List<Animal> animals) {
        return animals
            .stream()
            .collect(Collectors
                .toMap(
                    Animal::type,
                    Function.identity(),
                    BinaryOperator.maxBy(Comparator.comparing(Animal::weight))
                )
            );
    }

    public static Animal getKOldestAnimal(List<Animal> animals, int k) {
        return animals
            .stream()
            .sorted(Comparator.comparing(Animal::age).reversed())
            .skip(k - 1)
            .findFirst()
            .get();
    }

    public static Optional<Animal> getMostHeavyLowerKCm(List<Animal> animals, int k) {
        return animals
            .stream()
            .filter(v -> v.height() < k)
            .max(Comparator.comparing(Animal::weight));
    }

    public static Integer getSumOfPaws(List<Animal> animals) {
        return animals
            .stream()
            .reduce(0, (v1, v2) -> v1 + v2.paws(), Integer::sum);
    }

    public static List<Animal> getAnimalsWhichAgeNotEqualPaws(List<Animal> animals) {
        return animals
            .stream()
            .filter(v -> v.age() != v.paws())
            .toList();
    }

    public static List<Animal> getAnimalsWhichBitesAndHigherThatOneMeter(List<Animal> animals) {
        return animals
            .stream()
            .filter(v -> v.bites() && (v.height() > ONE_METER_IN_CM))
            .toList();
    }

    public static Integer countAnimalsWhichWeightMoreThanHeight(List<Animal> animals) {
        return Math.toIntExact(animals
            .stream()
            .filter(v -> v.weight() > v.height())
            .count());
    }

    public static List<Animal> getAnimalsWithNameOfTwoOrMoreWords(List<Animal> animals) {
        return animals
            .stream()
            .filter(v -> v.name().split("\\s+").length > 2)
            .toList();
    }

    public static boolean checkIsDogWithHeightMoreKCm(List<Animal> animals, int k) {
        return animals
            .stream()
            .anyMatch(v -> (v.height() > k) && Objects.equals(v.type(), Animal.Type.DOG));
    }

    public static Map<Animal.Type, Integer> getSumWeightEachTypeAgeFromKToL(List<Animal> animals, int k, int l) {
        return animals
            .stream()
            .filter(v -> (v.age() >= k) && (v.age() <= l))
            .collect(Collectors.toMap(
                Animal::type,
                Animal::weight,
                Integer::sum
            ));
    }

    public static List<Animal> getAnimalsSortedByTypeThenSexThenName(List<Animal> animals) {
        return animals
            .stream()
            .sorted(
                Comparator.comparing(Animal::type)
                    .thenComparing(Animal::sex)
                    .thenComparing(Animal::name)
            )
            .toList();
    }

    public static boolean checkSpidersBitesMoreThatDogs(List<Animal> animals) {
        Map<Animal.Type, Integer> animalBites = animals
            .stream()
            .collect(Collectors
                .groupingBy(
                    Animal::type,
                    Collectors.reducing(0, v -> (v.bites() ? 1 : 0), Integer::sum)
                )
            );

        return animalBites.get(Animal.Type.SPIDER) > animalBites.get(Animal.Type.DOG);
    }

    public static Animal getMostHeavyFish(List<List<Animal>> animals) {
        return animals
            .stream()
            .flatMap(Collection::stream)
            .reduce((v1, v2) ->
                !Objects.equals(v1.type(), Animal.Type.FISH)
                    ? v2
                    : !Objects.equals(v2.type(), Animal.Type.FISH)
                    ? v1
                    : v1.weight() > v2.weight()
                    ? v1 : v2)
            .orElse(null);
    }

    public static Map<String, Set<ValidationError>> getErrorsByNames(List<Animal> animals) {
        return animals
            .stream()
            .collect(
                Collectors.collectingAndThen(
                    Collectors.toMap(Animal::name, ValidationError::validate),
                    map -> {
                        map.values().removeIf(Set::isEmpty);
                        return map;
                    }
                ));
    }

    public static Map<String, String> getPrintableErrorsByNames(Map<String, Set<ValidationError>> errors) {
        return errors
            .entrySet()
            .stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                v -> v
                    .getValue()
                    .stream()
                    .map(ValidationError::toString)
                    .collect(Collectors.joining(", ")),
                (v1, v2) -> v1
            ));
    }

}
