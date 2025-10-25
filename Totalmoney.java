class Totalmoney {
    public int totalMoney(int n) {
        int weeks = n / 7;
        int rem = n % 7;
        int total = 7 * (weeks * (weeks + 1) / 2) + 21 * weeks;
        total += rem * (weeks + 1) + rem * (rem - 1) / 2;
        return total;
    }

    public static void main(String[] args) {
        Totalmoney s = new Totalmoney();
        System.out.println(s.totalMoney(10)); // Example test
    }
}

