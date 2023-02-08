import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Product } from '../common/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = 'http://localhost:8080/api/products';
  // private baseUrl = 'http://localhost:8080/api/products?size=100';  // by default, page size is 20 items, this change it to 100 items

  constructor(private httpClient: HttpClient) { }

  // map the JSON data from Spring Data REST to Product array
  getProductList(theCategoryId: number): Observable<Product[]> {

    // need to build URL based on category id
    const searchUrl = `${this.baseUrl}/search/findByCategoryId?id=${theCategoryId}`;

    return this.httpClient.get<GetRepsonse>(searchUrl).pipe(
      map(response => response._embedded.products)
    );
  }
}

// unwraps the JSON from Spring Data REST _embedded entry
interface GetRepsonse {
  _embedded: {
    products: Product[];
  }
}
