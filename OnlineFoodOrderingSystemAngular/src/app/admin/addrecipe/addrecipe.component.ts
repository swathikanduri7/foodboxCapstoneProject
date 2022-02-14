import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm, FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-addrecipe',
  templateUrl: './addrecipe.component.html',
  styleUrls: ['./addrecipe.component.css']
})
export class AddrecipeComponent implements OnInit {
  msg: any = [];
  avail: boolean;
  onerecipe: any;
  image;
  constructor(private http: HttpClient, private router: Router, private adminService: AdminService) { }

  ngOnInit(): void {
    this.check()
    this.onerecipe = this.adminService.temp;
  }
  check() {
    this.adminService.check().subscribe(
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


  onSubmit(f: NgForm) {
    if (!f.valid) {
      this.msg = "something went  wrong!!";
      this.avail = true;
      return;
    }
    const formData = new FormData();
    formData.append('file', this.image);
    formData.append('dishname', f.controls.dishname.value);
    formData.append('quantity', f.controls.quantity.value);
    formData.append('price', f.controls.price.value);
    this.http.post<any>('http://localhost:3000/admin/addrecipe', formData).subscribe(
      (res) => {
        this.adminService.avail = true;
        this.adminService.msg = "Successfully Added a recipe!!!"
        this.router.navigate(['/admin']);
        // console.log(res)
      }
      ,
      (error) => {

        if (error instanceof HttpErrorResponse) {
            this.adminService.msg = "Failed to Add a recipe!!!"
            this.router.navigate(['/admin'])

        }
        console.log(error);
      }
    );

  }
  selectImage(event) {
    console.log("image selected");
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.image = file;
    }
  }
}
