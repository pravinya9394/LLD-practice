class Diner {
    private final String name;
    private boolean isHungry = true;

    public Diner(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isHungry() {
        return isHungry;
    }

    public void eatWith(Spoon spoon, Diner partner) {
        while (isHungry) {
            // If spoon not owned, wait
            if (spoon.getOwner() != this) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ignored) {}
                continue;
            }

            // Be polite and let partner go first if they are hungry
            if (partner.isHungry) {
                System.out.println(name + ": You eat first, " + partner.getName());
                spoon.setOwner(partner);
                continue;
            }

            // Use the spoon
            spoon.use();
            isHungry = false;
            System.out.println(name + " is done eating.");
            spoon.setOwner(partner);
        }
    }
}