package mx.itchetumal.generacurp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider







class MainActivity : AppCompatActivity() {
    private lateinit var btnCalcular:Button
    private lateinit var btnlimpiar: Button
    private lateinit var edtNombre: EditText
    private lateinit var apPaterno: EditText
    private lateinit var apMaterno: EditText
    private lateinit var año: EditText
    private lateinit var mes: Spinner
    private lateinit var dia: EditText
    private lateinit var rbtnHombre: RadioButton
    private lateinit var rbtnMujer: RadioButton
    private lateinit var rbtngGenero: RadioGroup

    private lateinit var sexoPersona: String
    private lateinit var estado: Spinner

    private lateinit var rf: TextView

    private lateinit var rootViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.rootViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        btnCalcular= findViewById(R.id.calcular)
        btnlimpiar = findViewById(R.id.limpiar)
        edtNombre = findViewById(R.id.nombre)
        apPaterno = findViewById(R.id.apPaterno)
        apMaterno = findViewById(R.id.apMaterno)
        año = findViewById(R.id.año)
        mes = findViewById(R.id.mes)
        dia = findViewById(R.id.dia)
        rf = findViewById(R.id.rfc)
        rbtnHombre = findViewById(R.id.rbtnHombre)
        rbtnMujer = findViewById(R.id.rbtnMujer)
        rbtngGenero = findViewById(R.id.rbtngGenero)
        estado = findViewById(R.id.estado)


        val arrayAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.estados)
        )

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        val stmeses = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.meses)
        )
        stmeses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        estado.adapter = arrayAdapter
        estado.setSelection(0, false);
        mes.adapter = stmeses
        mes.setSelection(0, false);



        btnCalcular.setOnClickListener {

            if(edtNombre.text.toString() != ""){
                if(apPaterno.text.toString() != ""){
                    if(apMaterno.text.toString() !=""){
                        if(año.text.toString() != ""){

                            if(mes.selectedItem.toString() != "" && mes.selectedItemPosition != 0){
                                if(dia.text.toString() != ""){
                                    if(getGenro() != ""){
                                        if(estado.selectedItem.toString() != "" && estado.selectedItemPosition != 0){
                                            this.rootViewModel.calculaCurp(apPaterno.text.toString(), apMaterno.text.toString(),edtNombre.text.toString(),
                                                año.text.toString(), mes.selectedItem.toString(),dia.text.toString(), getGenro(),estado.selectedItem.toString())
                                        }else{
                                            Toast.makeText(this, "Seleccione el Estado de Nacimiento", Toast.LENGTH_SHORT).show()
                                        }
                                    }else{
                                        Toast.makeText(this, "Seleccione Genero de Nacimiento", Toast.LENGTH_SHORT).show()
                                    }
                                }else{
                                    Toast.makeText(this, "Proporcione Dia de Nacimiento", Toast.LENGTH_SHORT).show()
                                }
                            }else{
                                Toast.makeText(this, "Proporcione Mes de Nacimiento", Toast.LENGTH_SHORT).show()
                            }

                        }else{
                            Toast.makeText(this, "Proporcione Año de Nacimiento", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this, "Proporcione un apellido Materno", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "Proporcione un Apellido Paterno", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Proporcione un nombre", Toast.LENGTH_SHORT).show()
            }

       }
        btnlimpiar.setOnClickListener {
            edtNombre.setText("")
            apPaterno.setText("")
            apMaterno.setText("")
            año.setText("")
            dia.setText("")
            rbtnMujer.isChecked = false
            rbtnHombre.isChecked = false
            mes.setSelection(0, true)
            estado.setSelection(0, true)
            rf.text = "Sin CURP"
        }

        this.rootViewModel.curp.observe(this, {
            if(it == ""){
                rf.text = "Sin Curp"
            }else{
                rf.text = "Su curp es:\n $it"
            }
        })
    }

    fun getGenro(): String{
        return if(rbtnHombre.isChecked){
            "H"
        }else if(rbtnMujer.isChecked){
            "M"
        }else{
            ""
        }
    }

}