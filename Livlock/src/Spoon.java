class Spoon {
    private Diner owner;

    public Spoon(Diner owner) {
        this.owner = owner;
    }

    public synchronized void use() {
        System.out.println(owner.getName() + " is eating using the spoon.");
    }

    public Diner getOwner() {
        return owner;
    }

    public void setOwner(Diner d) {
        this.owner = d;
    }
}