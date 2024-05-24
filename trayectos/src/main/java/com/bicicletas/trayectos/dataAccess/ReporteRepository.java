import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bicicletas.trayectos.dataAccess.*;
import com.bicicletas.trayectos.logica.*;
import com.bicicletas.trayectos.modelo.*;

@Repository
public interface ReporteRepository
extends JpaRepository <Reporte, Integer> 
{
   
}
