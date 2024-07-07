package org.example;

import java.util.Collection;
import java.util.Optional;


public class MageRepository{
    private Collection<Mage> collection = null;

    public MageRepository(Collection<Mage> collection) {
        this.collection = collection;
    }

    //kontenerowy obiekt, który przechowuje obiekty klasy Mage
    //moga byc nullem, wiec jest to sposob na unikniecie NullPointerException
    //
    public Optional<Mage> find(String name) {
        for (Mage mage : collection) {
            if (mage.getName().equals(name)) {
                return Optional.of(mage);
            }
        }
        return Optional.empty();
    }

    public void delete(String name) {
        for (Mage mage : collection) {
            if (mage.getName().equals(name)) {
                collection.remove(mage);
                return;
            }
        }
        throw new IllegalArgumentException("not found");
    }

    public void save(Mage mage) {
        Boolean isMagePresent = checkIfMageIsPresent(mage);
        if (isMagePresent) {
            throw new IllegalArgumentException("bad request");
        }else{
            collection.add(mage);
        }
    }

    private Boolean checkIfMageIsPresent(Mage mage) {
        for (Mage m : collection) {
            if (m.getName().equals(mage.getName())) {
                return true;
            }
        }
        return false;
    }


}
