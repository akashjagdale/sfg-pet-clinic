package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    // @Autowired (optional in Constructor injection case)
    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Akash");
        owner1.setLastName("Jagdale");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Harry");
        owner2.setLastName("Potter");
        ownerService.save(owner2);
        System.out.println("Owners loaded..!");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Jon");
        vet1.setLastName("Snow");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Sheldon");
        vet2.setLastName("Cooper");
        vetService.save(vet2);

        System.out.println("Vets loaded...!");

    }
}
