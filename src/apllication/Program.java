package apllication;

import java.util.Date;

import model.entities.Igreja;
import model.entities.Membro;
import model.entities.Pgm;
import model.enums.Status;

public class Program {

	public static void main(String[] args) {

		Igreja ibjm = new Igreja(1, "Igeja Batista Jardim Maracanã", "Batista", "27247539000104");
		System.out.println(ibjm);

		System.out.println("##################");
		Membro membro = new Membro(1, "Edimar", new Date(), "M", "edimar.eds@gmail.com", "Rua Gaspar Kania, 50",
				"Guaraituba", "41997075959", "Helen", new Pgm(1, "Resgate", Status.ATIVO), null, null, null, ibjm);

		System.out.println(membro);

	}

}
