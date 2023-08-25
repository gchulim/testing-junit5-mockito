package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @DisplayName("Test Find All")
    @Test
    void findAll() {
        Visit visit = new Visit();

        Set<Visit> visits = new HashSet<>();
        visits.add(visit);

        when(visitRepository.findAll()).thenReturn(visits);

        Set<Visit> foundedVisits = service.findAll();

        verify(visitRepository).findAll();
        assertThat(foundedVisits).hasSize(1);
    }

    @DisplayName("Test Find By Id")
    @Test
    void findById() {
        Visit visit = new Visit();

        when(visitRepository.findById(1L)).thenReturn(Optional.of(visit));

        Visit foundVisit = service.findById(1L);

        verify(visitRepository).findById(anyLong());
        assertThat(foundVisit).isNotNull();
    }

    @DisplayName("Test Save")
    @Test
    void saveTest() {
        Visit visit = new Visit();

        when(visitRepository.save(any(Visit.class))).thenReturn(visit);
        Visit savedVisit = service.save(new Visit());

        verify(visitRepository).save(any(Visit.class));
        assertThat(savedVisit).isNotNull();
    }

    @DisplayName("Test Delete")
    @Test
    void delete() {
        Visit visit = new Visit(1L);
        service.delete(visit);

        verify(visitRepository).delete(any(Visit.class));
    }

    @DisplayName("Test Delete By Id")
    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(visitRepository).deleteById(anyLong());

    }
}