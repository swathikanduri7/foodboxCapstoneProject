import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { HttpErrorResponse } from '@angular/common/http';
import { CartService } from 'src/app/services/cart.service';
@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  arr: any[];
  recipes: any[];
  total:any;
  emptychechk:boolean;
  paymentHandler:any = null;

  constructor(private router: Router, private authService: AuthService,private cartService:CartService) { }

  ngOnInit(): void {
    this.invokeStripe();
    this.check()
    // this.getItem()
    this.empty()
  }

  check() {
    this.authService.check().subscribe(
      data => {
        console.log(data);
      },
      (error) => {

        if (error instanceof HttpErrorResponse) {

            this.router.navigate(['/login'])

        }
        console.log(error);
      }
    )
  }


  empty()
  {
    this.cartService.EmptyCheck().subscribe(
      data => {
        // console.log(data);
        if(data['msg']=="yes empty cart")
        {
          // console.log("empty cart");
          this.router.navigate(['/empty-cart'])
          return;
        }
        else
        {
          this.getItem();
        }
      },
      (error) => {
        console.log(error);
      }
    )
  }

  getItem()
  {
    this.authService.getCartItem().subscribe(
      data => {

        this.arr = data[0];

        this.total=this.arr['total'];

        this.recipes=this.arr['recipe']

        if(this.recipes.length==0)
        {
          this.router.navigate(['/empty-cart'])
          return;
        }

      },
      error => {
        console.log(error);
      }
    )
  }


  deletefromcart(recipe)
  {

    this.cartService.deleteRecipe(recipe).subscribe(
      data => {
        // console.log(data);
        if(data['msg']=="recipe deleted from the cart")
        {
          // console.log("hello");
          this.authService.msg="recipe deleted from the cart";
          this.authService.avail=true;
          this.router.navigate(['/userhome'])
        }
      },
      error => {

        console.log(error);
      }
    )
  }
  checkout()
  {
    this.initializePayment();
   
  }


  invokeStripe() {
    if(!window.document.getElementById('stripe-script')) {
      const script = window.document.createElement("script");
      script.id = "stripe-script";
      script.type = "text/javascript";
      script.src = "https://checkout.stripe.com/checkout.js";
      script.onload = () => {
        this.paymentHandler = (<any>window).StripeCheckout.configure({
          key: 'pk_test_51K5CjwSIYDqfPBTO6rpnautOVkXzl2zImxJCvCY26sdy7tLGl9D2KoBkPCrBWoTlhUvJCMPVN5NPOed7AmNn6xH600FuDAlLxL',
          locale: 'auto',
          token: function (stripeToken: any) {
            console.log(stripeToken)
            alert('Payment has been successfull!');
          }
         
        });
      }
      window.document.body.appendChild(script);
    }
   
  }

  initializePayment() {
   
    const paymentHandler = (<any>window).StripeCheckout.configure({
      key: 'pk_test_sLUqHXtqXOkwSdPosC8ZikQ800snMatYMb',
      locale: 'auto',
      token: function (stripeToken: any) {
        console.log({stripeToken})
        this.router.navigate(['/cart'])
        alert('Paid amount sucessfully!');
        this.loadSuccessPage();
      }
    });
  
    paymentHandler.open({
      name: 'Paying amount',
      description: 'Paying payment for the order',
      amount: this.total * 100
    }); 
    
  }

  loadSuccessPage(){
    this.cartService.deletecart().subscribe(
      data => {

        if(data['msg']=="order placed")
        {

          this.authService.msg="sucessfully order placed!!!!";
          this.authService.avail=true;
          this.router.navigate(['/userhome'])
        }
      },
      error => {
        console.log(error);
      }
    )
  }

  

}
