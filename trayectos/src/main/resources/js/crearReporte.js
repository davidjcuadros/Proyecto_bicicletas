const form = document.getElementById('reporteForm');

form.addEventListener('submit', async (event) => {
    event.preventDefault();

    const formData = new FormData(form);

    // Obtener los valores necesarios
    const latitud = marker ? marker.getLatLng().lat : '';
    const longitud = marker ? marker.getLatLng().lng : '';
    const idUsuario = 1; // Reemplaza con el ID de usuario correspondiente
    const descripcion = formData.get('description');

    formData.append('latitud', latitud);
    formData.append('longitud', longitud);
    formData.append('idUsuario', idUsuario);
    formData.append('descripcion', descripcion);

    // Realizar la solicitud POST
    try {
        const response = await fetch('/reporte/publicar', {
            method: 'POST',
            body: formData
        });

        if (response.ok) {
            const result = await response.text();
            console.log('Respuesta del servidor:', result);
            // Redirigir a la página correspondiente según la respuesta
            if (result === 'reportecreado') {
                window.location.href = '/reporte/reporte_creado';
            } else {
                window.location.href = '/reporte/reporte_nocreado';
            }
        } else {
            console.error('Error al enviar el reporte');
        }
    } catch (error) {
        console.error('Error de red:', error);
    }
});