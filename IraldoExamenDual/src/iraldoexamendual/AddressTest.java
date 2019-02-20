package iraldoexamendual;


public class AddressTest {

    @Test
    public void generate_key_pair_test() {
        Address address = new Address();
        assertNotNull(address);
        address.generateKeyPair();
        assertNotNull(address.getPK());
        assertNotNull(address.getSK());
    }


    @Test
    public void transferEZI_test() {

        Address rick = new Address();
        rick.generateKeyPair();

        rick.addEZI(20d);

        rick.transferEZI(20d);

        assertEquals(40d, rick.getBalance(), 0d);
    }

}
