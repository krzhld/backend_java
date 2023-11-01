package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static edu.hw4.AnimalOperations.checkIsDogWithHeightMoreKCm;
import static edu.hw4.AnimalOperations.checkSpidersBitesMoreThatDogs;
import static edu.hw4.AnimalOperations.countAnimalsByType;
import static edu.hw4.AnimalOperations.countAnimalsWhichWeightMoreThanHeight;
import static edu.hw4.AnimalOperations.getAnimalWithLongestName;
import static edu.hw4.AnimalOperations.getAnimalsSortedByTypeThenSexThenName;
import static edu.hw4.AnimalOperations.getAnimalsWhichAgeNotEqualPaws;
import static edu.hw4.AnimalOperations.getAnimalsWhichBitesAndHigherThatOneMeter;
import static edu.hw4.AnimalOperations.getAnimalsWithNameOfTwoOrMoreWords;
import static edu.hw4.AnimalOperations.getByHeightAsc;
import static edu.hw4.AnimalOperations.getErrorsByNames;
import static edu.hw4.AnimalOperations.getKFirstByWeightDesc;
import static edu.hw4.AnimalOperations.getKOldestAnimal;
import static edu.hw4.AnimalOperations.getMostHeavyAnimalsEachType;
import static edu.hw4.AnimalOperations.getMostHeavyFish;
import static edu.hw4.AnimalOperations.getMostHeavyLowerKCm;
import static edu.hw4.AnimalOperations.getMostSex;
import static edu.hw4.AnimalOperations.getPrintableErrorsByNames;
import static edu.hw4.AnimalOperations.getSumOfPaws;
import static edu.hw4.AnimalOperations.getSumWeightEachTypeAgeFromKToL;

public class AnimalOperationsTest {
    private static final List<Animal> animalList = List.of(
        new Animal("cute_spider1", Animal.Type.SPIDER, Animal.Sex.M, 1, 15, 34, true),
        new Animal("cute_spider2", Animal.Type.SPIDER, Animal.Sex.F, 2, 20, 54, true),
        new Animal("cute_spider3 extra name", Animal.Type.SPIDER, Animal.Sex.F, 40, 64, 51,
            true
        ),
        new Animal("cute_spider4", Animal.Type.SPIDER, Animal.Sex.M, 8, 90, 86, true),

        new Animal("kitty_cat1", Animal.Type.CAT, Animal.Sex.M, 2, 40, 100, false),
        new Animal("kitty_cat2 extra name", Animal.Type.CAT, Animal.Sex.F, 34, 40, 43,
            false
        ),
        new Animal("kitty_cat3", Animal.Type.CAT, Animal.Sex.M, 4, 23, 91, false),
        new Animal("kitty_cat4", Animal.Type.CAT, Animal.Sex.M, 54, 54, 112, false),

        new Animal("angry_bird1", Animal.Type.BIRD, Animal.Sex.F, 1, 24, 36, false),
        new Animal("angry_bird2", Animal.Type.BIRD, Animal.Sex.M, 3, 44, 52, false),
        new Animal("angry_bird3", Animal.Type.BIRD, Animal.Sex.F, 1, 2, 71, false),
        new Animal("angry_bird4", Animal.Type.BIRD, Animal.Sex.M, 1, 21, 21, false),

        new Animal("borzoi_dog1", Animal.Type.DOG, Animal.Sex.F, 1, 50, 120, false),
        new Animal("borzoi_dog2", Animal.Type.DOG, Animal.Sex.M, 1, 35, 55, true),
        new Animal("borzoi_dog3", Animal.Type.DOG, Animal.Sex.M, 53, 19, 87, false),
        new Animal("borzoi_dog4", Animal.Type.DOG, Animal.Sex.M, 4, 198, 23, true),
        new Animal("borzoooooooooooooooooi_doooooooooooog5", Animal.Type.DOG, Animal.Sex.M, 21, 30,
            123, false
        ),
        new Animal("borzoi_dog6", Animal.Type.DOG, Animal.Sex.F, 5, 36, 32, false),
        new Animal("borzoi_dog7", Animal.Type.DOG, Animal.Sex.F, 76, 36, 147, false),
        new Animal("borzoi_dog8", Animal.Type.DOG, Animal.Sex.M, 28, 86, 18, true),

        new Animal("flying_fish1", Animal.Type.FISH, Animal.Sex.F, 2, 42, 38, false),
        new Animal("flying_fish2", Animal.Type.FISH, Animal.Sex.M, 3, 65, 62, true),
        new Animal("flying_fish3", Animal.Type.FISH, Animal.Sex.F, 4, 76, 48, false)
    );

    private final static List<Animal> wrongAnimals = List.of(
        new Animal("test1", null, Animal.Sex.M, -10, 10, 10, null),
        new Animal("#$#$$", Animal.Type.DOG, null, 1, -10, -10, true),
        new Animal("test3", Animal.Type.BIRD, Animal.Sex.F, 1, 1, 1, true)
    );

    @Test
    public void testGetByHeightAsc() {
        List<Animal> animals = List.of(
            new Animal("alpha", Animal.Type.SPIDER, Animal.Sex.M, 1, 25, 10, true),
            new Animal("beta", Animal.Type.FISH, Animal.Sex.M, 4, 35, 100, false),
            new Animal("gamma", Animal.Type.CAT, Animal.Sex.F, 2, 55, 120, false),
            new Animal("delta", Animal.Type.DOG, Animal.Sex.M, 1, 45, 55, true)
        );

        List<Animal> result = getByHeightAsc(animals);

        assertThat(result).isEqualTo(List.of(
            new Animal("alpha", Animal.Type.SPIDER, Animal.Sex.M, 1, 25, 10, true),
            new Animal("beta", Animal.Type.FISH, Animal.Sex.M, 4, 35, 100, false),
            new Animal("delta", Animal.Type.DOG, Animal.Sex.M, 1, 45, 55, true),
            new Animal("gamma", Animal.Type.CAT, Animal.Sex.F, 2, 55, 120, false)
        ));
    }

    @Test
    void testGetKFirstByWeightDesc() {
        int k = 2;

        List<Animal> result = getKFirstByWeightDesc(animalList, k);

        assertThat(result).isEqualTo(List.of(
            new Animal("borzoi_dog7", Animal.Type.DOG, Animal.Sex.F, 76, 36, 147, false),
            new Animal("borzoooooooooooooooooi_doooooooooooog5", Animal.Type.DOG, Animal.Sex.M, 21, 30,
                123, false
            )
        ));
    }

    @Test
    void testCountAnimalsByType() {
        Map<Animal.Type, Long> result = countAnimalsByType(animalList);

        assertThat(result).isEqualTo(Map.of(
            Animal.Type.CAT, 4L,
            Animal.Type.FISH, 3L,
            Animal.Type.BIRD, 4L,
            Animal.Type.SPIDER, 4L,
            Animal.Type.DOG, 8L
        ));
    }

    @Test
    void testGetAnimalWithLongestName() {
        Animal result = getAnimalWithLongestName(animalList);

        assertThat(result.name()).isEqualTo("borzoooooooooooooooooi_doooooooooooog5");
    }

    @Test
    void testGetMostSex() {
        Animal.Sex result = getMostSex(animalList);

        assertThat(result).isEqualTo(Animal.Sex.M);
    }

    @Test
    void testGetMostHeavyAnimalsEachType() {
        Map<Animal.Type, Animal> thickestAnimals = getMostHeavyAnimalsEachType(animalList);

        assertThat(thickestAnimals).isEqualTo(Map.of(
            Animal.Type.CAT, new Animal("kitty_cat4", Animal.Type.CAT, Animal.Sex.M, 54, 54,
                112, false
            ),
            Animal.Type.FISH, new Animal("flying_fish2", Animal.Type.FISH, Animal.Sex.M, 3, 65,
                62, true
            ),
            Animal.Type.DOG, new Animal("borzoi_dog7", Animal.Type.DOG, Animal.Sex.F, 76, 36,
                147, false
            ),
            Animal.Type.SPIDER, new Animal("cute_spider4", Animal.Type.SPIDER, Animal.Sex.M, 8, 90,
                86, true
            ),
            Animal.Type.BIRD, new Animal("angry_bird3", Animal.Type.BIRD, Animal.Sex.F, 1, 2,
                71, false
            )
        ));
    }

    @Test
    void testGetKOldestAnimal() {
        int k = 3;

        Animal result = getKOldestAnimal(animalList, k);

        assertThat(result).isEqualTo(
            new Animal("borzoi_dog3", Animal.Type.DOG, Animal.Sex.M, 53, 19, 87, false)
        );
    }

    @Test
    void testGetMostHeavyLowerKCm() {
        int k = 60;

        Optional<Animal> result = getMostHeavyLowerKCm(animalList, k);

        assertThat(result).isEqualTo(Optional.of(
            new Animal("borzoi_dog7", Animal.Type.DOG, Animal.Sex.F, 76, 36, 147, false)
        ));
    }

    @Test
    void testGetSumOfPaws() {
        Integer paws = getSumOfPaws(animalList);

        Integer expectedResult = 4 * 8 + 4 * 4 + 2 * 4 + 8 * 4;
        assertThat(paws).isEqualTo(expectedResult);
    }

    @Test
    void testGetAnimalsWhichAgeNotEqualPaws() {
        List<Animal> result = getAnimalsWhichAgeNotEqualPaws(animalList);

        assertThat(result).isEqualTo(List.of(
            new Animal("cute_spider1", Animal.Type.SPIDER, Animal.Sex.M, 1, 15, 34, true),
            new Animal("cute_spider2", Animal.Type.SPIDER, Animal.Sex.F, 2, 20, 54, true),
            new Animal("cute_spider3 extra name", Animal.Type.SPIDER, Animal.Sex.F, 40, 64, 51,
                true
            ),
            new Animal("kitty_cat1", Animal.Type.CAT, Animal.Sex.M, 2, 40, 100, false),
            new Animal("kitty_cat2 extra name", Animal.Type.CAT, Animal.Sex.F, 34, 40, 43,
                false
            ),
            new Animal("kitty_cat4", Animal.Type.CAT, Animal.Sex.M, 54, 54, 112, false),
            new Animal("angry_bird1", Animal.Type.BIRD, Animal.Sex.F, 1, 24, 36, false),
            new Animal("angry_bird2", Animal.Type.BIRD, Animal.Sex.M, 3, 44, 52, false),
            new Animal("angry_bird3", Animal.Type.BIRD, Animal.Sex.F, 1, 2, 71, false),
            new Animal("angry_bird4", Animal.Type.BIRD, Animal.Sex.M, 1, 21, 21, false),
            new Animal("borzoi_dog1", Animal.Type.DOG, Animal.Sex.F, 1, 50, 120, false),
            new Animal("borzoi_dog2", Animal.Type.DOG, Animal.Sex.M, 1, 35, 55, true),
            new Animal("borzoi_dog3", Animal.Type.DOG, Animal.Sex.M, 53, 19, 87, false),
            new Animal("borzoooooooooooooooooi_doooooooooooog5", Animal.Type.DOG, Animal.Sex.M, 21, 30,
                123, false
            ),
            new Animal("borzoi_dog6", Animal.Type.DOG, Animal.Sex.F, 5, 36, 32, false),
            new Animal("borzoi_dog7", Animal.Type.DOG, Animal.Sex.F, 76, 36, 147, false),
            new Animal("borzoi_dog8", Animal.Type.DOG, Animal.Sex.M, 28, 86, 18, true),
            new Animal("flying_fish1", Animal.Type.FISH, Animal.Sex.F, 2, 42, 38, false),
            new Animal("flying_fish2", Animal.Type.FISH, Animal.Sex.M, 3, 65, 62, true),
            new Animal("flying_fish3", Animal.Type.FISH, Animal.Sex.F, 4, 76, 48, false)
        ));
    }

    @Test
    void testGetAnimalsWhichBitesAndHigherThatOneMeter() {
        List<Animal> result = getAnimalsWhichBitesAndHigherThatOneMeter(animalList);

        assertThat(result).isEqualTo(List.of(
            new Animal("borzoi_dog4", Animal.Type.DOG, Animal.Sex.M, 4, 198, 23, true)
        ));
    }

    @Test
    void testCountAnimalsWhichWeightMoreThanHeight() {
        Integer counter = countAnimalsWhichWeightMoreThanHeight(animalList);

        Integer expectedResult = 14;
        assertThat(counter).isEqualTo(expectedResult);
    }

    @Test
    void testGetAnimalsWithNameOfTwoOrMoreWords() {
        List<Animal> result = getAnimalsWithNameOfTwoOrMoreWords(animalList);

        assertThat(result).isEqualTo(List.of(
            new Animal("cute_spider3 extra name", Animal.Type.SPIDER, Animal.Sex.F, 40, 64, 51,
                true
            ),
            new Animal("kitty_cat2 extra name", Animal.Type.CAT, Animal.Sex.F, 34, 40, 43,
                false
            )
        ));
    }

    @Test
    void testCheckIsDogWithHeightMoreKCm() {
        int k = 76;

        boolean check = checkIsDogWithHeightMoreKCm(animalList, k);

        assertThat(check).isTrue();
    }

    @Test
    void testGetSumWeightEachTypeAgeFromKToL() {
        int k = 3;
        int l = 20;

        Map<Animal.Type, Integer> result = getSumWeightEachTypeAgeFromKToL(animalList, k, l);

        assertThat(result).isEqualTo(Map.of(
            Animal.Type.CAT, 91,
            Animal.Type.FISH, 110,
            Animal.Type.BIRD, 52,
            Animal.Type.SPIDER, 86,
            Animal.Type.DOG, 55
        ));
    }

    @Test
    void testGetAnimalsSortedByTypeThenSexThenName() {
        List<Animal> animals = List.of(
            new Animal("b", Animal.Type.FISH, Animal.Sex.M, 1, 85, 10, false),
            new Animal("c", Animal.Type.DOG, Animal.Sex.F, 1, 55, 10, false),
            new Animal("a", Animal.Type.FISH, Animal.Sex.F, 1, 25, 10, false),
            new Animal("c", Animal.Type.CAT, Animal.Sex.M, 1, 35, 10, false)
        );

        List<Animal> sorted = getAnimalsSortedByTypeThenSexThenName(animals);

        assertThat(sorted).isEqualTo(List.of(

            new Animal("c", Animal.Type.CAT, Animal.Sex.M, 1, 35, 10, false),
            new Animal("c", Animal.Type.DOG, Animal.Sex.F, 1, 55, 10, false),
            new Animal("b", Animal.Type.FISH, Animal.Sex.M, 1, 85, 10, false),
            new Animal("a", Animal.Type.FISH, Animal.Sex.F, 1, 25, 10, false)
        ));
    }

    @Test
    void testCheckSpidersBitesMoreThatDogs() {
        boolean checker = checkSpidersBitesMoreThatDogs(animalList);

        assertThat(checker).isTrue();
    }

    @Test
    void testGetMostHeavyFish() {
        Animal heaviestFish = new Animal("b", Animal.Type.FISH, Animal.Sex.M, 12, 55, 110,
            false
        );
        List<Animal> animalList1 = List.of(
            new Animal("b", Animal.Type.FISH, Animal.Sex.M, 12, 55, 110, false),
            new Animal("c", Animal.Type.FISH, Animal.Sex.M, 3, 20, 10, false)
        );
        List<Animal> animalList2 = List.of(
            new Animal("d", Animal.Type.FISH, Animal.Sex.M, 12, 43, 108, false),
            new Animal("r", Animal.Type.FISH, Animal.Sex.M, 3, 20, 10, false)
        );
        List<List<Animal>> allAnimals = List.of(animalList1, animalList2);

        Animal result = getMostHeavyFish(allAnimals);

        assertThat(result).isEqualTo(heaviestFish);

    }

    @Test
    void testGetErrorsByNames() {
        Map<String, Set<ValidationError>> errors = getErrorsByNames(wrongAnimals);

        Set<ValidationError> exceptedErrors1 = Set.of(
            new ValidationError(ErrorField.TYPE),
            new ValidationError(ErrorField.AGE)
        );
        assertThat(errors.containsKey("test1")).isTrue();
        assertThat(exceptedErrors1.equals(errors.get("test1"))).isTrue();
        Set<ValidationError> expectedErrors2 = Set.of(
            new ValidationError(ErrorField.NAME),
            new ValidationError(ErrorField.SEX),
            new ValidationError(ErrorField.HEIGHT),
            new ValidationError(ErrorField.WEIGHT)
        );
        assertThat(errors.containsKey("#$#$$")).isTrue();
        assertThat(expectedErrors2.equals(errors.get("#$#$$"))).isTrue();
    }

    @Test
    void testGetPrintableErrorsByNames() {
        Map<String, Set<ValidationError>> errors = getErrorsByNames(wrongAnimals);

        Map<String, String> printableAnimalErrors = getPrintableErrorsByNames(errors);

        assertThat(printableAnimalErrors.containsKey("test1")).isTrue();
        assertThat(printableAnimalErrors.get("test1")).isEqualTo("TYPE, AGE");
        assertThat(printableAnimalErrors.containsKey("#$#$$")).isTrue();
        assertThat(printableAnimalErrors.get("#$#$$")).isEqualTo("NAME, SEX, HEIGHT, WEIGHT");
    }
}
