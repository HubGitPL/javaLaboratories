package entity;

import jakarta.persistence.*;
import java.util.*;
@Entity
@Table(name = "tower")
public class Tower {
    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "height")
    private int height;

    @OneToMany(mappedBy = "tower", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Mage> mages;

    public Tower() {
    }

    public Tower(String name, int height) {
        this.name = name;
        this.height = height;
        this.mages = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Mage> getMages() {
        return mages;
    }

    public void setMages(List<Mage> mages) {
        this.mages = mages;
    }

    public void addMage(Mage mage) {
        mages.add(mage);
        mage.setTower(this);
    }

    public void removeMage(Mage mage) {
        mages.remove(mage);
    }

    public void clearMages() {
        mages.clear();
    }

    public void printMages() {
        for (Mage mage : mages) {
            System.out.println(mage.getName());
        }
    }

    @Override
    public String toString() {
        return "Tower{" +
                "name= '" + name + '\'' +
                ", height= " + height +
                '}';
    }
}
