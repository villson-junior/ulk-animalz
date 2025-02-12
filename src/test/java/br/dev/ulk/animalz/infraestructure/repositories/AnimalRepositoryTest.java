package br.dev.ulk.animalz.infraestructure.repositories;

import br.dev.ulk.animalz.domain.enumerations.StatusEnum;
import br.dev.ulk.animalz.domain.models.Animal;
import br.dev.ulk.animalz.domain.models.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AnimalRepositoryTest {

    @Mock
    private AnimalRepository animalRepository;
    @Mock
    private GroupRepository groupRepository;

    private Animal animal;
    private Group group;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        group = Group.builder()
                .name("Mammals")
                .build();
        group.setId(1L);
        group = groupRepository.save(group);
        animal = Animal.builder()
                .scientificName("Panthera leo")
                .specie("Lion")
                .size(1.8)
                .mass(190.0)
                .status(StatusEnum.ACTIVE)
                .group(group)
                .build();
        animal.setId(1L);
    }

    @Test
    void findAnimalsByGroupId() {
        when(animalRepository.findAnimalsByGroupId(anyLong())).thenReturn(Arrays.asList(animal));
        List<Animal> result = animalRepository.findAnimalsByGroupId(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Panthera leo", result.get(0).getScientificName());
        verify(animalRepository, times(1)).findAnimalsByGroupId(anyLong());
    }

    @Test
    void findByGroupNameIgnoreCase() {
        when(animalRepository.findByGroupNameIgnoreCase(anyString())).thenReturn(Arrays.asList(animal));
        List<Animal> result = animalRepository.findByGroupNameIgnoreCase("mammals");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Panthera leo", result.get(0).getScientificName());
        verify(animalRepository, times(1)).findByGroupNameIgnoreCase(anyString());
    }
}