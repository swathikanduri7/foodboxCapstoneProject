import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';
import { Recipe } from '../../recipe'
@Component({
  selector: 'app-viewrecipe',
  templateUrl: './viewrecipe.component.html',
  styleUrls: ['./viewrecipe.component.css']
})
export class ViewrecipeComponent implements OnInit {
  public recipes: Recipe[];
  avail: boolean;
  arr: any[];

  constructor(private router: Router, private adminService: AdminService) { }

  ngOnInit(): void {
    this.check()
    this.readRecipe()
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

  }

  readRecipe() {
    this.adminService.getAllRecipe().subscribe(
      data => {
        this.arr = data['msg'];
        this.recipes = data['msg'];

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

  deleterecipe(recipe) {


    var recipeid = recipe._id;
    this.adminService.deleterecipe(recipeid).subscribe(
      data => {
        // console.log(data);
        this.adminService.avail = true;
        this.adminService.msg = "Successfully Deleted a Recipe!!!";
        this.router.navigate(['/admin']);
      },
      (error) => {

        if (error instanceof HttpErrorResponse) {

          this.router.navigate(['/admin'])

        }
        console.log(error);
      }
    )
  }
  editrecipe(recipe) {
    this.adminService.temp = recipe;
    this.router.navigate(['/admin/editrecipe']);
  }
}
