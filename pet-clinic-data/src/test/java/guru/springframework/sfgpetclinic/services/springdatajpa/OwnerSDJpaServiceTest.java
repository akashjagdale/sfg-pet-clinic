package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

	private static final long OWNER_ID = 1L;

	private static final String LAST_NAME = "Smith";

	@Mock
	OwnerRepository ownerRepository;

	@Mock
	PetRepository petRepository;

	@Mock
	PetTypeRepository petTypeRepository;

	@InjectMocks
	OwnerSDJpaService service;

	Owner returnOwner;

	@BeforeEach
	void setUp() throws Exception {
		returnOwner = Owner.builder().id(OWNER_ID).lastName(LAST_NAME).build();
	}

	@Test
	void testFindByLastName() {
		when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
		Owner smith = service.findByLastName(LAST_NAME);
		assertEquals(LAST_NAME, smith.getLastName());
		verify(ownerRepository).findByLastName(any());
	}

	@Test
	void testFindById() {
		when(ownerRepository.findById(org.mockito.Mockito.anyLong())).thenReturn(Optional.of(returnOwner));
		Owner owner = service.findById(OWNER_ID);

		assertNotNull(owner);
	}

	@Test
	void testFindByIdNotFound() {
		when(ownerRepository.findById(org.mockito.Mockito.anyLong())).thenReturn(Optional.empty());
		Owner owner = service.findById(OWNER_ID);

		assertNull(owner);
	}

	@Test
	void testSave() {
		Owner owerToSave = Owner.builder().id(OWNER_ID).build();

		when(ownerRepository.save(any())).thenReturn(returnOwner);

		Owner savedOwner = service.save(owerToSave);

		assertNotNull(savedOwner);

		verify(ownerRepository).save(any());

	}

	@Test
	void testFindAll() {
		Set<Owner> returnOwnerSet = new HashSet<Owner>();
		returnOwnerSet.add(Owner.builder().id(1L).build());
		returnOwnerSet.add(Owner.builder().id(2L).build());

		when(ownerRepository.findAll()).thenReturn(returnOwnerSet);

		Set<Owner> owners = service.findAll();

		assertNotNull(owners);
		assertEquals(2, owners.size());
	}

	@Test
	void testDelete() {
		service.delete(returnOwner);

		// Default for times is 1
		verify(ownerRepository, times(1)).delete(any());
	}

	@Test
	void testDeleteById() {
		service.deleteById(OWNER_ID);

		verify(ownerRepository).deleteById(anyLong());
	}

}
