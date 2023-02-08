import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/common/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list-grid.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  currentCategoryId: number = 1;

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute) { } // the current active route that loaded the component. Useful for accessing route parameters

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.listProducts();
    })
  }

  listProducts() {
    // check if "id" parameter is available
    // route - use the activated route
    // snapshot - state of route at this given moment in time
    // paramMap - map of all route parameters
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has('id');

    if (hasCategoryId) {
      // get the "id" param string, convert string to a number using the "+" symbol
      // "!" is the non-null assertion operator, tells compiler that the object is not null
      this.currentCategoryId = +this.route.snapshot.paramMap.get('id')!;
    } else {
      // no category id available... default to category id 1
      this.currentCategoryId = 1;
    }

    // get the products for the given category id
    this.productService.getProductList(this.currentCategoryId).subscribe( // asyn assign results to the Product array
      data => {
        this.products = data;
      }
    )
  }

}
