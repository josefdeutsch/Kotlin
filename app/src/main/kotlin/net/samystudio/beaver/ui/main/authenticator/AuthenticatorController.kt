package net.samystudio.beaver.ui.main.authenticator

import android.view.View
import android.widget.Button
import butterknife.BindView
import com.jakewharton.rxbinding2.view.clicks
import net.samystudio.beaver.R
import net.samystudio.beaver.ui.base.controller.BaseDataPushController

class AuthenticatorController : BaseDataPushController<AuthenticatorControllerViewModel>()
{
    override val layoutViewRes: Int = R.layout.fragment_authenticator
    override val viewModelClass: Class<AuthenticatorControllerViewModel> =
        AuthenticatorControllerViewModel::class.java

    @BindView(R.id.sign_in)
    lateinit var signInButton: Button
    @BindView(R.id.sign_up)
    lateinit var signUpButton: Button

    override fun onViewCreated(view: View)
    {
        super.onViewCreated(view)

        viewModel.addUserFlow(
            signInButton.clicks().map { AuthenticatorUserFlow.SignIn("hello", "world") })
        viewModel.addUserFlow(
            signUpButton.clicks().map { AuthenticatorUserFlow.SignUp("hello", "world") })
    }

    override fun dataPushStart()
    {
        // TODO show loader
    }

    override fun dataPushSuccess()
    {
    }

    override fun dataPushError(throwable: Throwable)
    {
        //  getGenericErrorDialog(context!!).showNow(fragmentManager, AlertDialog::class.java.name)
    }

    override fun dataPushTerminate()
    {
        // TODO hide loader
    }

    companion object
    {
        /*fun newInstance(requestCode: Int?): AuthenticatorController
        {
            val fragment = AuthenticatorController()
            requestCode?.let { fragment.setTargetActivity(requestCode) }
            fragment.setDialogStyle(DialogFragment.STYLE_NO_TITLE, 0)
            return fragment
        }*/
    }
}