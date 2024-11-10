package mk.ukim.finki.wp_aud1.model.exceptions;

public class ManufacturerNotFound extends Exception{
    public ManufacturerNotFound(Long ID) {
        System.out.println("ID" + ID);
    }
}
