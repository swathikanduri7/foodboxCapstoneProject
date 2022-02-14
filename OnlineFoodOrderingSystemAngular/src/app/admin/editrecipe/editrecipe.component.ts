import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-editrecipe',
  templateUrl: './editrecipe.component.html',
  styleUrls: ['./editrecipe.component.css']
})
export class EditrecipeComponent implements OnInit {
  msg: any = [];
  avail: boolean;
  onerecipe: any;
  dishname: any;
  quantity: any;
  price: any;
  pn: any;
  ps: any;
  pp: any;
  id: any;
  image;
  constructor(private http: HttpClient, private router: Router, private adminService: AdminService) { }

  ngOnInit(): void {
    this.check()
    this.onerecipe = this.adminService.temp;

    this.dishname = this.onerecipe.dishname;
    this.quantity = this.onerecipe.quantity;
    this.price = this.onerecipe.price;
    this.id = this.onerecipe._id;
  }

  check() {
    this.adminService.check().subscribe(
      data => {
        console.log(data);
      },
      (error) => {

        if (error instanceof HttpErrorResponse) {

            this.router.navigate(['/admin'])

        }
        console.log(error);
      }
    )
    // console.log();
  }

  onSubmit(f: NgForm) {
    if (!f.valid) {
      this.msg = "something went  wrong!!";
      this.avail = true;
      return;
    }
    const formData = new FormData();
    formData.append('id', this.id);

    if (f.controls.dishname.value) {
      // console.log("yes name");
      formData.append('dishname', f.controls.dishname.value);
      this.pn = f.controls.dishname.value;
    }
    else {
      // console.log("no name");
      formData.append('dishname', this.dishname);
      this.pn = this.dishname;
    }
    if (f.controls.quantity.value) {
      // console.log("yes size");
      formData.append('quantity', f.controls.quantity.value);
      this.ps = f.controls.quantity.value;
    }
    else {
      // console.log("no size");
      formData.append('quantity', this.quantity);
      this.ps = this.quantity;
    }

    if (f.controls.price.value) {
      // console.log("yes price");
      formData.append('price', f.controls.price.value);
      this.pp = f.controls.price.value;
    }
    else {
      // console.log("no price");
      formData.append('price', this.price);
      this.pp = this.price;
    }


    if (f.controls.recipepic.value) {
      // console.log("yes image");
      formData.append('file', this.image);

      // *************
      this.http.post<any>('http://localhost:3000/admin/editrecipewithimage', formData).subscribe(
        (res) => {
          this.adminService.avail = true;
          this.adminService.msg = "Successfully Edited a recipe!!!"
          this.router.navigate(['/admin']);
          console.log(res)
        }
        ,
        (error) =>{

          if(error instanceof HttpErrorResponse)
          {

              this.router.navigate(['/admin'])

          }
          console.log(error);
        }
      );

    }
    else {

      this.http.get<any>('http://localhost:3000/admin/editrecipewithoutimage?id=' + this.id + '&recipename=' + this.pn + '&quantity=' + this.ps + '&price=' + this.pp
      ).subscribe(
        (res) => {
          this.adminService.avail = true;
          this.adminService.msg = "Successfully Edited a recipe!!!"
          this.router.navigate(['/admin']);
          console.log(res)
        }
        ,
        (error) =>{

          if(error instanceof HttpErrorResponse)
          {
              this.router.navigate(['/admin'])

          }
          console.log(error);
        }
      );
    }


  }


  selectImage(event) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.image = file;
    }
  }
}
