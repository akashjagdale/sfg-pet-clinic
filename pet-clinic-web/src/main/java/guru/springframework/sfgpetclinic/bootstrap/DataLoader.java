package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    // @Autowired (optional in Constructor injection case)
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }

    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType saveCatPetType = petTypeService.save(cat);

        PetType bird = new PetType();
        bird.setName("Bird");
        PetType saveBirdPetType = petTypeService.save(bird);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Akash");
        owner1.setLastName("Jagdale");
        owner1.setAddress("234 Baker Street");
        owner1.setCity("New York");
        owner1.setTelephone("4352923482");

        Pet akashPet = new Pet();
        akashPet.setName("Rambo");
        akashPet.setPetType(saveDogPetType);
        akashPet.setOwner(owner1);
        akashPet.setBirthDate(LocalDate.now());
        owner1.getPets().add(akashPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Harry");
        owner2.setLastName("Potter");
        owner2.setAddress("563 Baker Street");
        owner2.setCity("New York");
        owner2.setTelephone("32422342");

        Pet harryPet = new Pet();
        harryPet.setName("Hedwig");
        harryPet.setPetType(saveBirdPetType);
        harryPet.setOwner(owner2);
        harryPet.setBirthDate(LocalDate.now());
        owner2.getPets().add(harryPet);
        ownerService.save(owner2);

        Owner owner3 = new Owner();
        owner3.setFirstName("Brad");
        owner3.setLastName("Pitt");
        owner3.setAddress("234 Tower Square");
        owner3.setCity("Paris");
        owner3.setTelephone("9876564333");

        Pet bradPet = new Pet();
        bradPet.setName("Kitty");
        bradPet.setPetType(saveCatPetType);
        bradPet.setOwner(owner3);
        bradPet.setBirthDate(LocalDate.now());
        owner3.getPets().add(bradPet);
        ownerService.save(owner3);

        System.out.println("Owners loaded..!");

        Visit catVisit = new Visit();
        catVisit.setPet(bradPet);
        catVisit.setLocalDate(LocalDate.now());
        catVisit.setDescription("Sneeze Kitty");
        visitService.save(catVisit);

        Vet vet1 = new Vet();
        vet1.setFirstName("Jon");
        vet1.setLastName("Snow");
        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Sheldon");
        vet2.setLastName("Cooper");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);

        Vet vet3 = new Vet();
        vet3.setFirstName("Ronald");
        vet3.setLastName("Wisely");
        vet3.getSpecialities().add(savedDentistry);
        vetService.save(vet3);

        System.out.println("Vets loaded...!");
    }
}
