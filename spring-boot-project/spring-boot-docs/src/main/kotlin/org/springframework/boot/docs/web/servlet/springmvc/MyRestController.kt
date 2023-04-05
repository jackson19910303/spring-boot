/*
 * Copyright 2012-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.docs.web.servlet.springmvc

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/users")
class MyRestController(private val userRepository: UserRepository, private val customerRepository: CustomerRepository) {

	@GetMapping("/{userId}")
	fun getUser(@PathVariable userId: Long): User {
		return userRepository.findById(userId).get()
	}

	@GetMapping("/{userId}/customers")
	fun getUserCustomers(@PathVariable userId: Long): List<Customer> {
		return userRepository.findById(userId).map(customerRepository::findByUser).get()
	}

	@DeleteMapping("/{userId}")
	fun deleteUser(@PathVariable userId: Long) {
		userRepository.deleteById(userId)
	}

}

