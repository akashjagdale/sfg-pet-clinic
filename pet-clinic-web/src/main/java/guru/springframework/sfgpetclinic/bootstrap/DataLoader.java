package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    // @Autowired (optional in Constructor injection case)
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType saveCatPetType = petTypeService.save(cat);

        PetType parrot = new PetType();
        parrot.setName("Parrot");
        PetType saveParrotPetType = petTypeService.save(parrot);

        Owner owner1 = new Owner();
        owner1.setFirstName("Akash");
        owner1.setLastName("Jagdale");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Harry");
        owner2.setLastName("Potter");
        ownerService.save(owner2);

        Owner owner3 = new Owner();
        owner3.setFirstName("Brad");
        owner3.setLastName("Pitt");
        ownerService.save(owner3);
        System.out.println("Owners loaded..!");

        Vet vet1 = new Vet();
        vet1.setFirstName("Jon");
        vet1.setLastName("Snow");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Sheldon");
        vet2.setLastName("Cooper");
        vetService.save(vet2);

        Vet vet3 = new Vet();
        vet3.setFirstName("Ronald");
        vet3.setLastName("Wisely");
        vetService.save(vet3);

        System.out.println("Vets loaded...!");

    }
}
