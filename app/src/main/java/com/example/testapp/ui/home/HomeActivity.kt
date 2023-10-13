package com.example.testapp.ui.home

import android.os.Bundle
import com.example.testapp.R
import com.example.testapp.base.PermissionRequestActivity
import com.ringcentral.video.RcvEngine

class HomeActivity : PermissionRequestActivity(),HomeMvpView {
    lateinit var presenter: HomePresenter<HomeMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_view)
        presenter=HomePresenter()
        presenter.onAttach(this)
        presenter.getData()
      RcvEngine.create(this.applicationContext,"cpkEWp0iGz7aYrD1nm7e4o","fnoo2LhDpFAfBUHt3HPqu93HjPiHqT1sgejeQaeYK82m")
      RcvEngine.instance().setAuthToken("eyJraWQiOiI4NzYyZjU5OGQwNTk0NGRiODZiZjVjYTk3ODA0NzYwOCIsInR5cCI6IkpXVCIsImFsZyI6IlJTMjU2In0.eyJhdWQiOiJodHRwczovL3BsYXRmb3JtLmRldnRlc3QucmluZ2NlbnRyYWwuY29tL3Jlc3RhcGkvb2F1dGgvdG9rZW4iLCJzdWIiOiI4MDY5NTcwMDUiLCJpc3MiOiJodHRwczovL3BsYXRmb3JtLmRldnRlc3QucmluZ2NlbnRyYWwuY29tIiwiZXhwIjozODM2Nzk3MjY4LCJpYXQiOjE2ODkzMTM2MjEsImp0aSI6IldIUVA1UGw2UW9XOFVnZUQ4cDNNY0EifQ.ZbUu3jYa1NJut8b__i9KL97EcVJ3SJSyqLpY1mNII4QoKGTM-eeZqyFMsw_VbJ9jmGYA_Pm0GImErQt4JtC9Ltf7-3DuqucUzpvdWnR0GwOnrbkQ7WeQVQSrJNCtqaRNv-yyORVdTAkFgOs8FusCcAGOFMTfcShDBH4dsUs1iwN1OFpdgaP_EMjcLxc_woNZ3doQHk7Efhg8Bj8yVc3vFUzDxucPtZ7Xal7WuwoEwCgqhYhhUI5BxwhIqX8GbLT4wK6mgrFnmRUeYjRtbroQ3ZrTZRkWvqJmAx4lDrSfGWcjEHxdo_vR8kAseNWP_IQz8uTyyZehickrvQXQAe1vPg",true)

    }

    override fun onDataSuccess(data: Boolean) {
        println("Here in on Data Success")
    }

    override fun loaderVisibility(visibility: Boolean) {

    }
}