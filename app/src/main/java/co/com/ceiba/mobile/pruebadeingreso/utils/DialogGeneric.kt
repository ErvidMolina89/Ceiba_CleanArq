package co.com.ceiba.mobile.pruebadeingreso.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.Guideline
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import co.com.ceiba.mobile.pruebadeingreso.R

class DialogGeneric private constructor(): DialogFragment() {

    private val T = "DialogGeneric"

    companion object{
        private var showingDialog = false
        private var instance : DialogGeneric?= null

        fun getInstance() : DialogGeneric {
            if(instance == null )
            {
                instance =
                    DialogGeneric()
            }
            return instance!!
        }
    }


    private var mainContainer : View?= null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mainContainer = inflater.inflate(R.layout.dialog_generic,null,false)
        isCancelable = false

        findsViewElements()
        fillView()
        addListeners()
        return mainContainer
    }

    private var image_dialog : ImageView?= null
    private var details_message : TextView?= null
    private var title : TextView?= null
    private var btn_ok : Button?= null
    private var btn_cancel : Button?= null
    private var guideline : Guideline?= null


    private fun findsViewElements(){

        image_dialog    = mainContainer?.findViewById(R.id.imagen_dialogo)
        details_message = mainContainer?.findViewById(R.id.detalle_mensaje)
        title           = mainContainer?.findViewById(R.id.Titulo)
        btn_ok          = mainContainer?.findViewById(R.id.boton1_dialogo)
        btn_cancel      = mainContainer?.findViewById(R.id.boton2_dialogo)
        guideline       = mainContainer?.findViewById(R.id.linea_guia)

    }


    private fun fillView(){
        selectIcon()
        fillTitle()
        fillMessage()
        fillBtnOk()
        fillBtnCancel()
    }

    private fun selectIcon(){
        image_dialog?.setImageResource(typeDialog.getIcono())
    }

    private fun fillTitle(){
        if(routeTitle == null ){
            title?.visibility = View.GONE
            return
        }
        title?.visibility = View.VISIBLE
        title?.setText(routeTitle!!)
    }

    private fun fillMessage(){
        if(routeText == null ){
            details_message?.visibility = View.GONE
            return
        }
        details_message?.visibility = View.VISIBLE
        details_message?.setText(routeText!!)
    }

    private fun fillBtnOk(){
        if(routeTextBtnOk == null ){
            btn_ok?.visibility = View.GONE
            return
        }
        btn_ok?.setText(routeTextBtnOk!!)
    }

    private fun fillBtnCancel(){
        if(routeTextBtnCancel == null ){
            btn_cancel?.visibility = View.GONE
            guideline?.setGuidelinePercent(1.toFloat())
            return
        }
        guideline?.setGuidelinePercent((0.5).toFloat())
        btn_cancel?.visibility = View.VISIBLE
        btn_cancel?.setText(routeTextBtnCancel!!)
    }


    private fun addListeners(){

        mainContainer?.setOnClickListener {
            if(routeTextBtnOk != null ){ return@setOnClickListener }
            dismiss()
            invokesActionOk?.invoke()
            cleanElementsOfSight()
        }

        btn_ok?.setOnClickListener {
            dismiss()
            invokesActionOk?.invoke()
            cleanElementsOfSight()
        }
        btn_cancel?.setOnClickListener {
            dismiss()
            invokesActionCancel?.invoke()
            cleanElementsOfSight()
        }
    }

    private fun cleanElementsOfSight(){
        routeTextBtnCancel = null
        invokesActionOk = null
        invokesActionCancel =  null
    }

    override fun dismiss() {
        showingDialog = false
        if(fragmentManager == null ){
            return
        }
        super.dismiss()
        super.dismissAllowingStateLoss()
    }


    override fun onStart() {
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        super.onStart()
    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            if(showingDialog){ return }
            if(isAdded){ return }
            showingDialog = true
            super.show(manager, tag)
        }catch (e : Exception) {
            e.printStackTrace()
        }
    }

    enum class TypeDialog{
        ERROR,
        WARNING,
        OK
        ;

        fun getIcono(): Int{
            return when(this){
                OK -> R.drawable.ic_check
                WARNING -> R.drawable.ic_warning
                ERROR -> R.drawable.ic_close
            }
        }
    }

    private var invokesActionOk:(()->Unit)?= null
    fun withActionBtnOk(actionOk : ()->Unit) : DialogGeneric {
        this.invokesActionOk = actionOk
        return this
    }

    private var invokesActionCancel : (()->Unit)?= null
    fun withActionBtnCancel(actionCancel : ()->Unit) : DialogGeneric {
        this.invokesActionCancel = actionCancel
        return this
    }

    private @StringRes
    var routeTitle : Int ?= null
    fun withTitle(@StringRes routeString : Int): DialogGeneric {
        this.routeTitle = routeString
        return this
    }

    private var routeText : String ?= null
    fun withText(routeString : String): DialogGeneric {
        this.routeText = routeString
        return this
    }

    private @StringRes
    var routeTextBtnOk : Int ?= null
    fun withTextBtnOk(@StringRes routeString : Int): DialogGeneric {
        this.routeTextBtnOk = routeString
        return this
    }

    private @StringRes
    var routeTextBtnCancel : Int ?= null
    fun withTextBtnCancel(@StringRes routeString : Int): DialogGeneric {
        this.routeTextBtnCancel = routeString
        return this
    }

    private var typeDialog : TypeDialog = TypeDialog.OK
    fun withTypeDialog(typeDialog : TypeDialog = TypeDialog.OK) : DialogGeneric {
        this.typeDialog = typeDialog
        return this
    }

    fun showDialogue(fragmentManager: FragmentManager, etiqueta : String){
        show(fragmentManager,etiqueta)
    }

}