import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bicicletas.trayectos.dataAccess.*;
import com.bicicletas.trayectos.logica.*;
import com.bicicletas.trayectos.modelo.*;
@Service
@RequestMapping("/reporte")
public class ReporteServices {
    @Autowired
    UsuarioRepository usuario;

    @Autowired
    ReporteRepository reporte;

    //Crecion de usuario:
    @GetMapping("/creacionReporte")
    public Integer publicar(@RequestParam String ubicacion, @RequestParam Integer idUsuario, @RequestParam String descripcion, @RequestParam List<String> imagenes){
        Reporte rpt = new Reporte();
        rpt.setUbicacion(ubicacion);
        rpt.setId(idUsuario);
        rpt.setDescripcion(descripcion);
        //rpt.setUsuario(null);
        rpt.setImagenes(imagenes);
        rpt.setLikes(0);

        if(reporte.save(rpt).equals(null)){
            return -1;
        }
        else{
            reporte.save(rpt);
            return rpt.getId();
        }

    }

    public void cargueImagenes (){
        
    }
}