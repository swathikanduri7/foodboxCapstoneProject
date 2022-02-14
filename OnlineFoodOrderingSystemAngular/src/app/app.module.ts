import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule,HTTP_INTERCEPTORS} from '@angular/common/http';
import { AppRoutingModule ,routingComponents} from './app-routing.module';
import { AppComponent } from './app.component';
import { FamousComponent } from './ind/famous/famous.component';
import { ChefsComponent } from './ind/chefs/chefs.component';
import { OfferComponent } from './ind/offer/offer.component';
import { RegisterComponent } from './auth/register/register.component';
import { LoginComponent } from './auth/login/login.component';
import { IndComponent } from './admin/ind/ind.component';
import { AdminhomeComponent } from './admin/adminhome/adminhome.component';
import {AuthModule} from './auth/auth.module';
import { ViewuserComponent } from './admin/viewuser/viewuser.component';
import { AaComponent } from './aa/aa.component';
import { AddrecipeComponent } from './admin/addrecipe/addrecipe.component';
import { FormsModule } from '@angular/forms';
import { ViewrecipeComponent } from './admin/viewrecipe/viewrecipe.component';
import { EditrecipeComponent } from './admin/editrecipe/editrecipe.component';
import { NavbarComponent } from './user/navbar/navbar.component';
import { GlowComponent } from './user/glow/glow.component';

// search module
import { Ng2SearchPipeModule } from 'ng2-search-filter';

// toster
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { UserhomeComponent } from './user/userhome/userhome.component';
import { AuthGuard } from './auth/auth.guard';
import { TokenInterceptorService } from './services/token-interceptor.service';
import { AdminGuard } from './admin/admin.guard';
import { CartComponent } from './user/cart/cart.component';
import { MyprofileComponent } from './user/myprofile/myprofile.component';
import { EditprofileComponent } from './user/editprofile/editprofile.component';
import { ChangepasswordComponent } from './user/changepassword/changepassword.component';
import { UserofferComponent } from './user/useroffer/useroffer.component';
import { UserfeedbackComponent } from './user/userfeedback/userfeedback.component';
import { ViewfeedbackComponent } from './admin/viewfeedback/viewfeedback.component';
import { EmptycartComponent } from './user/emptycart/emptycart.component';
import { ViewoneorderComponent } from './admin/viewoneorder/viewoneorder.component';
import { ViewoneuserComponent } from './admin/viewoneuser/viewoneuser.component';
import { SearchComponent } from './user/userhome/search/search.component';


@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    FamousComponent,
    ChefsComponent,
    OfferComponent,
    RegisterComponent,
    LoginComponent,
    IndComponent,
    AdminhomeComponent,
    ViewuserComponent,
    AaComponent,
    AddrecipeComponent,
    ViewrecipeComponent,
    EditrecipeComponent,
    NavbarComponent,
    GlowComponent,
    UserhomeComponent,
    CartComponent,
    MyprofileComponent,
    EditprofileComponent,
    ChangepasswordComponent,
    UserofferComponent,
    UserfeedbackComponent,
    ViewfeedbackComponent,
    EmptycartComponent,
    ViewoneorderComponent,
    ViewoneuserComponent,
    SearchComponent,

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    AuthModule,
    FormsModule,
    BrowserAnimationsModule, // required animations  module
    ToastrModule.forRoot(),
	Ng2SearchPipeModule,
  ],
  providers: [AuthGuard,AdminGuard,{
    provide:HTTP_INTERCEPTORS,
    useClass:TokenInterceptorService,
    multi:true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
