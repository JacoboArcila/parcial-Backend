import com.dh.dao.impl.OdontologooDaoH2;
import com.dh.model.Odontologo;
import com.dh.model.service.OdontologoService;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class TestParcial {

    private static OdontologoService odontologoService = new OdontologoService(new OdontologooDaoH2());
    @BeforeClass
    public static void cargarDatos() throws Exception {
        Odontologo odontologo = odontologoService.guardar(new Odontologo( "lorena", "Angel", "15"));
    }

    @Test
    public void testeandoOdontologos() throws Exception {
        List<Odontologo> odontologos = odontologoService.buscarTodos();

        Assert.assertFalse(odontologos.isEmpty());
        Assert.assertTrue(odontologos.size() >= 3);

        System.out.println(odontologos);


    }


}
