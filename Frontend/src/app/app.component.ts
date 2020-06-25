import {Component, OnInit} from '@angular/core';
import {User} from "./user.model";
import {HttpClient} from "@angular/common/http";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
    public users: User[];

    constructor(private http: HttpClient) {
    }

    ngOnInit(): void {
        this.setUsers();
    }

    private setUsers(): void {
        this.http.get<User[]>('/api/users')
            .subscribe(users => this.users = users);
    }
}
